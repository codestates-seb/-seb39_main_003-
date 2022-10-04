package com.web.MyPetForApp.auth.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.web.MyPetForApp.auth.dto.AuthDetails;
import com.web.MyPetForApp.auth.provider.TokenProvider;
import com.web.MyPetForApp.member.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    private TokenProvider tokenProvider;

    private final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, TokenProvider tokenProvider) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.info("인증이나 권한 필요한 주소가 요청 되었습니다.");

        String jwtToken = resolveToken(request ,"Authorization");

        if(jwtToken != null) {
            TokenProvider.JwtCode accValidResult = tokenProvider.validateToken(jwtToken, "access");

            if(accValidResult == TokenProvider.JwtCode.ACCESS) {
                Authentication authentication = tokenProvider.getAuthentication(jwtToken);
                if(authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                }
//        else {
//            super.doFilterInternal(request, response, filterChain);
            } else if (accValidResult == TokenProvider.JwtCode.EXPIRED) {
                String refreshToken = resolveToken(request, "refresh");
                // refresh Token이 요청헤더에 있다면
                if(refreshToken != null) {
                    // refresh Token을 검증
                    TokenProvider.JwtCode refValidResult = tokenProvider.validateToken(refreshToken, "refresh");
                    // 검증이 통과하면
                    if(refValidResult != TokenProvider.JwtCode.DENIED) {
                        // refresh Token 재발급
                        String newRefreshToken = tokenProvider.updateRefreshToken(refreshToken);
                        if(newRefreshToken != null) {
                            response.addHeader("refresh", "Bearer " + newRefreshToken);
                            // Access Token 재발급
                            Authentication authentication = tokenProvider.getAuthentication(refreshToken);
                            AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
                            response.addHeader("Authorization", "Bearer " + tokenProvider.createAccessToken(authDetails.getMember().getMemberId(),authDetails.getEmail()));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            logger.info("리프레시 토큰 & 액세스 토큰 최신화 완료");
                        }
                        filterChain.doFilter(request, response);
                    }
                } else
                    // refresh Token이 요청헤더에 없다면
                    // 클라이언트에 Access Token이 만료되었음을 알리고, refresh Token을 요청헤더에 달라고 요청(예외 처리)
                    throw new TokenExpiredException("토큰 만료", Instant.now());
            }
        } else {
            logger.info("유효한 토큰을 찾지 못하였습니다, uri : {}", request.getRequestURI());
            filterChain.doFilter(request, response);
        }

    }

    private String resolveToken(HttpServletRequest request, String header) {
        String jwtHeader = request.getHeader(header);

        if(jwtHeader != null && jwtHeader.startsWith("Bearer")) {
            return jwtHeader.replace("Bearer ", "");
        }
        return null;
    }
}

package com.web.MyPetForApp.auth.filter;

import com.web.MyPetForApp.auth.provider.TokenProvider;
import com.web.MyPetForApp.member.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    private TokenProvider tokenProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, TokenProvider tokenProvider) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("인증이나 권한 필요한 주소가 요청 되었습니다.");

        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = jwtHeader.replace("Bearer ", "");
        Authentication authentication = tokenProvider.getAuthentication(jwtToken);

        if(authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            super.doFilterInternal(request, response, filterChain);
        }
    }
}

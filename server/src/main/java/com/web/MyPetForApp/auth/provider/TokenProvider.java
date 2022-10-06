package com.web.MyPetForApp.auth.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.web.MyPetForApp.auth.dto.AuthDetails;
import com.web.MyPetForApp.auth.entity.RefreshToken;
import com.web.MyPetForApp.auth.repository.RefreshTokenRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class TokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final RefreshTokenRepository refreshTokenRepository;

    private MemberRepository memberRepository;

    public TokenProvider(MemberRepository memberRepository, RefreshTokenRepository refreshTokenRepository) {
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Value("{jwt.secret}")
    private String accessKey;

    private String refreshKey = "refresh " + accessKey;

//    @Value("{jwt.token-validity-in-seconds}")
//    private int tokenValidityInMilliseconds;

//    private Key key;

    @Override
    public void afterPropertiesSet() {
//        byte[] keyBytes = Decoders.BASE64.decode(secret);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String memberId, String email) {

            return JWT.create()
                    .withSubject("MyPet JWT Access Token")
//                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 60)))
                    .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000)))
                    .withClaim("memberId", memberId)
                    .withClaim("email", email)
                    .sign(Algorithm.HMAC512(accessKey));

//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        long now = (new Date()).getTime();
//        Date validity = new Date(now + this.tokenValidityInMilliseconds);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim(AUTHORITIES_KEY, authorities)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .setExpiration(validity)
//                .compact();
    }

    private String createRefreshToken(String memberId, String email) {

            return JWT.create()
                    .withSubject("MyPet JWT Refresh Token")
                    .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 60 * 24 * 14)))
                    .withClaim("memberId", memberId)
                    .withClaim("email", email)
                    .sign(Algorithm.HMAC512(refreshKey));
    }

//    public String createOAuthAccessToken(Authentication authentication) {
//
//        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
//        Member findMember = memberRepository.findByMemberName(oAuth2User.getName()).orElseThrow(
//                () -> new IllegalArgumentException("회원이 존재하지 않습니다."));
//        return JWT.create()
//                .withSubject("MyPet JWT Access Token")
//                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000)))
//                .withClaim("id", findMember.getMemberId())
//                .withClaim("email", findMember.getEmail())
//                .sign(Algorithm.HMAC512(accessKey));
//    }
//
//    public String createOAuthRefreshToken(Authentication authentication) {
//
//        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
//        Member findMember = memberRepository.findByMemberName(oAuth2User.getName()).orElseThrow(
//                () -> new IllegalArgumentException("회원이 존재하지 않습니다."));
//        return JWT.create()
//                .withSubject("MyPet JWT Access Token")
//                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000)))
//                .withClaim("id", findMember.getMemberId())
//                .withClaim("email", findMember.getEmail())
//                .sign(Algorithm.HMAC512(accessKey));
//    }

    @Transactional
    public String renewalRefreshToken(String memberId, String email) {
        String newRefreshToken = createRefreshToken(memberId, email);
        // 기존 토큰이 있다면 바꿔주고, 없다면 토큰을 만들어준다.
        refreshTokenRepository.findByEmail(email)
                .ifPresentOrElse(
                        r -> {r.changeToken(newRefreshToken);
                        logger.info("기존 리프레시 토큰 변환");},
                        () -> {
                            RefreshToken toSaveToken = RefreshToken.createToken(email, newRefreshToken);
                            logger.info("새로운 리프레시 토큰 저장 | member's email : {}, token : {}", toSaveToken.getEmail(), toSaveToken.getToken() );
                            refreshTokenRepository.save(toSaveToken);
                        }
                );
        return newRefreshToken;
    }

    @Transactional
    public String updateRefreshToken(String refreshToken) throws RuntimeException {
        // refresh Token을 DB에 저장된 토큰과 비교
        Authentication authentication = getAuthentication(refreshToken);
        AuthDetails authdetails = (AuthDetails) authentication.getPrincipal();
        RefreshToken findRefreshToken = refreshTokenRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("email : " + authentication.getName() + " was not found"));

        // 토큰이 일치한다면
        if(findRefreshToken.getToken().equals(refreshToken)) {
            // 새로운 토큰 생성
            String newRefreshToken = createRefreshToken(authdetails.getMember().getMemberId(), authdetails.getEmail());
            findRefreshToken.changeToken(newRefreshToken);
            return newRefreshToken;
        } else {
            logger.info("refresh Token이 일치하지 않습니다.");
            return null;
        }
    }




    public Authentication getAuthentication(String token) {
//        Claims claims = Jwts
//                .parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());

        String email = JWT.decode(token).getClaim("email").asString();

        if(email != null) {
            Member memberEntity = memberRepository.findByEmail(email).orElseThrow(
                    () -> new UsernameNotFoundException(email + "데이터베이스에서 찾을 수 없습니다.")
            );

            AuthDetails authDetails = new AuthDetails(memberEntity);

            return new UsernamePasswordAuthenticationToken(authDetails, null, authDetails.getAuthorities());
        } else {
            return null;
        }

//        Member memberEntity = Member.builder()
//                .email(claims.getSubject())
//                .password("")
//                .roles(claims.get(AUTHORITIES_KEY).toString())
//                .build();

//        AuthDetails authDetails = new AuthDetails(memberEntity);

//        return new UsernamePasswordAuthenticationToken(authDetails, token, authorities);
    }

    public JwtCode validateToken(String token, String tokenType) {
        String key = tokenType.equals("access") ? accessKey : refreshKey;

        try {
                JWT.require(Algorithm.HMAC512(key)).build().verify(token);
            return JwtCode.ACCESS;
        } catch (TokenExpiredException e) {
            logger.info("만료된 토큰입니다.");
            return JwtCode.EXPIRED;
        } catch (JWTVerificationException e) {
            logger.info("토큰 검증에 실패하였습니다.");
            return JwtCode.DENIED;
        }
    }

    public static enum JwtCode {
        DENIED,
        ACCESS,
        EXPIRED;
    }
}

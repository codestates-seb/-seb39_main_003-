package com.web.MyPetForApp.auth.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.web.MyPetForApp.auth.dto.AuthDetails;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";

    private MemberRepository memberRepository;

    public TokenProvider(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Value("{jwt.secret}")
    private String secret;

//    @Value("{jwt.token-validity-in-seconds}")
//    private int tokenValidityInMilliseconds;

    private Key key;

    @Override
    public void afterPropertiesSet() {
//        byte[] keyBytes = Decoders.BASE64.decode(secret);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {

        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();

        return JWT.create()
                .withSubject("MyPet JWT token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 60)))
                .withClaim("id", authDetails.getMember().getMemberId())
                .withClaim("memberName", authDetails.getUsername())
                .sign(Algorithm.HMAC512(secret));

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

        String memberName = validateAndGetMemberNameToken(token);

        if(memberName != null) {
            Member memberEntity = memberRepository.findByMemberName(memberName).orElseThrow(
                    () -> new UsernameNotFoundException(memberName + "데이터베이스에서 찾을 수 없습니다.")
            );

            AuthDetails authDetails = new AuthDetails(memberEntity);

            return new UsernamePasswordAuthenticationToken(authDetails, null, authDetails.getAuthorities());
        } else {
            return null;
        }

//        Member memberEntity = Member.builder()
//                .memberName(claims.getSubject())
//                .password("")
//                .roles(claims.get(AUTHORITIES_KEY).toString())
//                .build();

//        AuthDetails authDetails = new AuthDetails(memberEntity);

//        return new UsernamePasswordAuthenticationToken(authDetails, token, authorities);
    }

    public String  validateAndGetMemberNameToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("memberName").asString();
        } catch (ExpiredJwtException e) {
            logger.info("손상된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원하지 않는 JWT 토큰 형식입니다.");
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (SignatureVerificationException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return null;
    }
}

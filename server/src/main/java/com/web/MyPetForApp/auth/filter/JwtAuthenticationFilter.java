package com.web.MyPetForApp.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.MyPetForApp.auth.provider.TokenProvider;
import com.web.MyPetForApp.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("로그인 시도");

        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getMemberName(), member.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        System.out.println("로그인 성공 유저 처리");

        String jwtToken = tokenProvider.createToken(authResult);
        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}

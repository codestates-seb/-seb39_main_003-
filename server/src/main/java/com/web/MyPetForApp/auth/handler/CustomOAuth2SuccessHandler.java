package com.web.MyPetForApp.auth.handler;

import com.web.MyPetForApp.auth.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        String memberId = (String) oAuth2User.getAttributes().get("memberName");
        String email = (String) oAuth2User.getAttributes().get("email");

        String accessToken = tokenProvider.createAccessToken(memberId, email);
        String refreshToken = tokenProvider.renewalRefreshToken(memberId, email);

        System.out.println("소셜 로그인 성공 유저 : " + email);

        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("refresh", "Bearer " + refreshToken);
//        response.addHeader("Access-Control-Allow-Origin", "*");

//        getRedirectStrategy().sendRedirect(request, response, "http://49.165.248.183:3000");
//        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080");
//        getRedirectStrategy().sendRedirect(request, response, "https://seb39-main-003-gamma.vercel.app");
//        getRedirectStrategy().sendRedirect(request, response, "https://seb39-main-003-gadt7n9o7-nomga.vercel.app");
        getRedirectStrategy().sendRedirect(request, response, "https://seb39-main-003-izr1fgrp2-nomga.vercel.app");
    }
}

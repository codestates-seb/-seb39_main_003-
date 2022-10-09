package com.web.MyPetForApp.auth.handler;

import com.web.MyPetForApp.auth.provider.TokenProvider;
import com.web.MyPetForApp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        String email = (String) oAuth2User.getAttributes().get("email");
        String memberId = memberRepository.findMemberIdByEmail(email);

        String accessToken = tokenProvider.createAccessToken(memberId, email);
        String refreshToken = tokenProvider.renewalRefreshToken(memberId, email);

        System.out.println("소셜 로그인 성공 유저 : " + email);

        String uri = createURI(accessToken, refreshToken).toString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParmas = new LinkedMultiValueMap<>();
        queryParmas.add("access_token", "Bearer " + accessToken);
        queryParmas.add("refresh_token","Bearer " + refreshToken);
// https://seb39-main-003-8d0kgy3xy-nomga.vercel.app/
        return UriComponentsBuilder
                .newInstance()
                .scheme("https")
//                .host("localhost")
                .host("seb39-main-003-bsihsudrk-nomga.vercel.app")
//                .port("3000")
                .path("/")
                .queryParams(queryParmas)
                .build()
                .toUri();
    }
}
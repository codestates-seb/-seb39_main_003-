package com.web.MyPetForApp.auth.config;

import com.web.MyPetForApp.auth.error.ErrorhandlerFilter;
import com.web.MyPetForApp.auth.error.JwtAccessDeniedHandler;
import com.web.MyPetForApp.auth.error.JwtAuthenticationEntryPoint;
import com.web.MyPetForApp.auth.filter.JwtAuthenticationFilter;
import com.web.MyPetForApp.auth.filter.JwtAuthorizationFilter;
import com.web.MyPetForApp.auth.handler.CustomOAuth2SuccessHandler;
import com.web.MyPetForApp.auth.handler.JwtAddLogoutHandler;
import com.web.MyPetForApp.auth.provider.TokenProvider;
import com.web.MyPetForApp.auth.service.CustomOAuth2UserService;
import com.web.MyPetForApp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final ErrorhandlerFilter errorhandlerFilter;
    private final JwtAddLogoutHandler jwtAddLogoutHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 디폴트인 세션, 쿠키 생성 허용 -> 불허
                .and()
                .httpBasic().disable()
                .apply(new JwtLogin())
                .and()
                .addFilterBefore(errorhandlerFilter, JwtAuthorizationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .logout()
                .addLogoutHandler(jwtAddLogoutHandler)
                .logoutSuccessUrl("/api/v1/user/test")
                .and()
                .oauth2Login()
                .successHandler(customOAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return httpSecurity.build();
    }

    public class JwtLogin extends AbstractHttpConfigurer<JwtLogin, HttpSecurity> {
        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {
            AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
            httpSecurity
                    .addFilter(corsFilter)
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, tokenProvider))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository, tokenProvider));
        }
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

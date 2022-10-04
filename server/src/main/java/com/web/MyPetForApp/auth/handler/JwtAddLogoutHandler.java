package com.web.MyPetForApp.auth.handler;

import com.web.MyPetForApp.auth.repository.RefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAddLogoutHandler implements LogoutHandler {

    private final RefreshTokenRepository refreshTokenRepository;

    private final Logger logger = LoggerFactory.getLogger(JwtAddLogoutHandler.class);

    public JwtAddLogoutHandler(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String email = request.getParameter("email");
        refreshTokenRepository.deleteByEmail(email);
        logger.info("해당 유저의 refresh 토큰이 삭제되었습니다. email : {}", email);
    }
}

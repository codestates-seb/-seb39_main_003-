package com.web.MyPetForApp.auth.repository;

import com.web.MyPetForApp.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByEmail(String email);

    void deleteByEmail(String email);
}

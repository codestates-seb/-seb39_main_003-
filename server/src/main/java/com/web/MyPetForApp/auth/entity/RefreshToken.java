package com.web.MyPetForApp.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long refreshTokenId;

    private String email;
    private String token;

    public  static RefreshToken createToken(String email, String token) {
        return RefreshToken.builder().email(email).token(token).build();
    }

    public void changeToken(String token) {
        this.token = token;
    }
}

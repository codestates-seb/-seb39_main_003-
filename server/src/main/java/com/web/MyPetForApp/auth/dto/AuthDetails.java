package com.web.MyPetForApp.auth.dto;

import com.web.MyPetForApp.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
@Builder
public class AuthDetails implements UserDetails, OAuth2User {
    private final Member member;
    private Map<String, Object> attributes;

    // 일반 로그인
    public AuthDetails(Member member) {
        this.member = member;
    }
    // OAuth2 로그인

    public AuthDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> String.valueOf(member.getMemberRole()));
        return authorities;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        member.getRoleList().forEach(n -> {
//            authorities.add(() -> n);
//        });
//        return authorities;
//    }
    @Override
    public Map<String, Object> getAttributes() {
    return attributes;
}

    @Override
    public String getName() {return (String) attributes.get("name");}

    public String getEmail() {return member.getEmail();}

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}

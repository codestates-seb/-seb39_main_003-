package com.web.MyPetForApp.auth.service;

import com.web.MyPetForApp.auth.dto.AuthDetails;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email + "데이터베이스에서 찾을 수 없습니다.")
        );
        return new AuthDetails(memberEntity);
    }
}

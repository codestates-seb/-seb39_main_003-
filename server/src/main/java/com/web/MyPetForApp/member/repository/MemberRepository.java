package com.web.MyPetForApp.member.repository;

import com.web.MyPetForApp.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByMemberName(String name);
    public Optional<Member> findByEmail(String email);
}

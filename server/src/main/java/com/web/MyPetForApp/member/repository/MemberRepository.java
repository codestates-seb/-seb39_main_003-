package com.web.MyPetForApp.member.repository;

import com.web.MyPetForApp.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByMemberName(String name);
}

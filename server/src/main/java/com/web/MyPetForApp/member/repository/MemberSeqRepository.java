package com.web.MyPetForApp.member.repository;

import com.web.MyPetForApp.member.entity.MemberSeq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSeqRepository extends JpaRepository<MemberSeq, Integer> {
}

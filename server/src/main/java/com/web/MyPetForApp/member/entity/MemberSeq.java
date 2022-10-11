package com.web.MyPetForApp.member.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(
        name = "MEMBER_SEQ_GEN",
        sequenceName = "MEMBER_SQE",
        initialValue = 1,
        allocationSize = 1
)
public class MemberSeq {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "MEMBER_SEQ_GEN")
    public int memberSeq;
}

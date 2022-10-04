package com.web.MyPetForApp.qna.entity;

import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.board.entity.Board;
import com.web.MyPetForApp.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String answerContent;

    @Column(unique = true, nullable = false)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void changeMember(Member member) {
        this.member = member;
    }

    public void updateAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}

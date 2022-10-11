package com.web.MyPetForApp.qna.entity;


import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private boolean checked;

    @Column(nullable = false)
    private String questionTitle;

    @Column(nullable = false)
    private String questionContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void changeMember(Member member) {
        this.member = member;
    }

    public void changeItem(Item item) {
        this.item = item;
    }

    public void updateQna(Question question){
        if(question.getQuestionTitle() != null) this.questionTitle = question.getQuestionTitle();
        if(question.getQuestionContent() != null) this.questionContent = question.getQuestionContent();
        if(question.isChecked()) this.checked = question.isChecked();
    }
}

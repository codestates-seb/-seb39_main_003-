package com.web.MyPetForApp.qna.entity;


import com.web.MyPetForApp.basetime.BaseTimeEntity;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Qna extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @Column(nullable = false)
    private boolean checked;

    @Column(nullable = false)
    private String qnaTitle;

    @Column(nullable = false)
    private String qnaContent;

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

    public void updateQna(Qna qna){
        if(qna.getQnaTitle() != null) this.qnaTitle = qna.getQnaTitle();
        if(qna.getQnaContent() != null) this.qnaContent = qna.getQnaContent();
        if(qna.isChecked()) this.checked = qna.isChecked();
    }
}

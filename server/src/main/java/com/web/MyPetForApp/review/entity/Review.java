package com.web.MyPetForApp.review.entity;

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
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private int startCnt;

    @Column
    private String photo;

    @Column
    private String reviewContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public void changeMember(Member member) {
        this.member = member;
    }

    public void changeItem(Item item) {
        this.item = item;
    }

    public void updateReview(Review review){
        if(review.getPhoto() != null) this.photo = review.getPhoto();
        if(review.getReviewContent() != null) this.reviewContent = review.getReviewContent();
        if(review.getStartCnt() != 0) this.startCnt = review.getStartCnt();
    }
}

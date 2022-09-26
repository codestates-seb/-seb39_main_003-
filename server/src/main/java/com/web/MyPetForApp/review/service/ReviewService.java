package com.web.MyPetForApp.review.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.service.ItemService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.service.MemberService;
import com.web.MyPetForApp.review.entity.Review;
import com.web.MyPetForApp.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    public Review createReview(Review review, String memberId, String itemId){
        Member member = memberService.findVerifiedMember(memberId);
        Item item = itemService.findVerifiedItem(itemId);
        review.changeMember(member);
        review.changeItem(item);
        return reviewRepository.save(review);
    }

    public Review findReview(Long reviewId){
        return findVerifiedReview(reviewId);
    }

    public Page<Review> findReviews(String itemId, int page, int size){

        Item item = itemService.findVerifiedItem(itemId);
        return reviewRepository.findAllByItem(item, PageRequest.of(page, size,
                Sort.by("createdAt").descending()));
    }

    public Review updateReview(Long reviewId, Review review, String memberId){
        Review findReview = findVerifiedReview(reviewId);
        if(!memberId.equals(findReview.getMember().getMemberId())){
            throw  new IllegalArgumentException("리뷰는 작성자만 수정할 수 있습니다.");
        }
        findReview.updateReview(review);
        return findReview;
    }

    public void deleteReview(Long reviewId){
        Review review = findVerifiedReview(reviewId);
        reviewRepository.delete(review);
    }

    public Review findVerifiedReview(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));
    }
}

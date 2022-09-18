package com.web.MyPetForApp.review.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;

    public Review createReview(Review review, Long memberId, Long itemId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        review.setMember(member);
        review.setItem(item);
        return reviewRepository.save(review);
    }

    public Review findReview(Long reviewId){
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));
    }

    public Page<Review> findReviews(Long itemId, int page, int size){

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        return reviewRepository.findAllByItem(item, PageRequest.of(page, size,
                Sort.by("createdAt").descending()));
    }

    public Review updateReview(Long reviewId, Review review, Long memberId){
        Review findReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));
        if(memberId != findReview.getMember().getMemberId()){
            throw  new IllegalArgumentException("리뷰는 작성자만 수정할 수 있습니다.");
        }
        findReview.update(review);
        return findReview;
    }

    public void deleteReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));
        reviewRepository.delete(review);
    }

}

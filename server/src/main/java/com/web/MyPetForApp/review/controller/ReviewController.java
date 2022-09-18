package com.web.MyPetForApp.review.controller;

import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.review.dto.ReviewDto;
import com.web.MyPetForApp.review.entity.Review;
import com.web.MyPetForApp.review.mapper.ReviewMapper;
import com.web.MyPetForApp.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    @PostMapping
    public ResponseEntity postReview(@RequestBody ReviewDto.Post requestBody){
        Review review = mapper.reviewPostDtoToReview(requestBody);
        Long memberId = requestBody.getMemberId();
        Long itemId = requestBody.getItemId();
        Review savedReview = reviewService.createReview(review, memberId, itemId);
        ReviewDto.Response response = mapper.reviewToReviewResponseDto(savedReview);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity getReview(@PathVariable Long reviewId){
        Review review = reviewService.findReview(reviewId);
        ReviewDto.Response response = mapper.reviewToReviewResponseDto(review);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getReviews(@RequestParam Long itemId,
                                     @RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "8") int size){
        Page<Review> pageReviews = reviewService.findReviews(itemId, page-1, size);
        List<Review> reviews = pageReviews.getContent();
        List<ReviewDto.Response> response = mapper.reviewsToReviewResponseDto(reviews);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageReviews), HttpStatus.OK);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity patchReview(@PathVariable Long reviewId,
                                      @RequestBody ReviewDto.Patch requestBody){
        Long memberId = requestBody.getMemberId();
        Review review = reviewService.updateReview(reviewId, mapper.reviewPatchDtoToReview(requestBody), memberId);
        ReviewDto.Response response = mapper.reviewToReviewResponseDto(review);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

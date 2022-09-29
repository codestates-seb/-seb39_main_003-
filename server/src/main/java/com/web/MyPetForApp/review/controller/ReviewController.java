package com.web.MyPetForApp.review.controller;

import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.review.dto.ReviewDto;
import com.web.MyPetForApp.review.entity.Review;
import com.web.MyPetForApp.review.mapper.ReviewMapper;
import com.web.MyPetForApp.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상품 리뷰 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    @Operation(summary = "상품 리 등록")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping
    public ResponseEntity postReview(@RequestBody ReviewDto.Post requestBody){
        Review review = mapper.reviewPostDtoToReview(requestBody);
        String memberId = requestBody.getMemberId();
        String itemId = requestBody.getItemId();
        Review savedReview = reviewService.createReview(review, memberId, itemId);
        ReviewDto.Response response = mapper.reviewToReviewResponseDto(savedReview);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    @Operation(summary = "하나의 상품 리 정보 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/{reviewId}")
    public ResponseEntity getReview(@PathVariable Long reviewId){
        Review review = reviewService.findReview(reviewId);
        ReviewDto.Response response = mapper.reviewToReviewResponseDto(review);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Operation(summary = "상품 리뷰 리스트 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping
    public ResponseEntity getReviews(@Parameter(description = "상품 식별번호") @RequestParam String itemId,
                                     @Parameter(description = "현재 페이지") @RequestParam(required = false, defaultValue = "1") int page,
                                     @Parameter(description = "한 페이지 당 상품 리뷰 수") @RequestParam(required = false, defaultValue = "8") int size){
        Page<Review> pageReviews = reviewService.findReviews(itemId, page-1, size);
        List<Review> reviews = pageReviews.getContent();
        List<ReviewDto.Response> response = mapper.reviewsToReviewResponseDto(reviews);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageReviews), HttpStatus.OK);
    }

    @Operation(summary = "상품 리 정보 수정")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping("/{reviewId}")
    public ResponseEntity patchReview(@PathVariable Long reviewId,
                                      @RequestBody ReviewDto.Patch requestBody){
        String memberId = requestBody.getMemberId();
        Review review = reviewService.updateReview(reviewId, mapper.reviewPatchDtoToReview(requestBody), memberId);
        ReviewDto.Response response = mapper.reviewToReviewResponseDto(review);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @Operation(summary = "상품 리뷰 삭제")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    )
    @DeleteMapping("/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

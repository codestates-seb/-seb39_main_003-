package com.web.MyPetForApp.review.mapper;

import com.web.MyPetForApp.review.dto.ReviewDto;
import com.web.MyPetForApp.review.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public Review reviewPostDtoToReview(ReviewDto.Post requestBody){
        return Review.builder()
                .startCnt(requestBody.getStarCnt())
//                .photo(requestBody.getPhoto())
                .reviewContent(requestBody.getReviewContent())
                .build();
    }

    public Review reviewPatchDtoToReview(ReviewDto.Patch requestBody){
        return Review.builder()
                .startCnt(requestBody.getStarCnt())
//                .photo(requestBody.getPhoto())
                .reviewContent(requestBody.getReviewContent())
                .build();
    }

    public ReviewDto.Response reviewToReviewResponseDto(Review review){
        return ReviewDto.Response.builder()
                .reviewId(review.getReviewId())
                .startCnt(review.getStartCnt())
                .reviewContent(review.getReviewContent())
                .createAt(review.getCreatedAt())
                .modifiedAt(review.getModifiedAt())
                .itemId(review.getItem().getItemId())
                .nickName(review.getMember().getNickName())
                .build();
    }

    public List<ReviewDto.Response> reviewsToReviewResponseDto(List<Review> reviews){
        return reviews
                .stream()
                .map(review -> ReviewDto.Response
                        .builder()
                        .reviewId(review.getReviewId())
                        .startCnt(review.getStartCnt())
                        .reviewContent(review.getReviewContent())
                        .createAt(review.getCreatedAt())
                        .modifiedAt(review.getModifiedAt())
                        .itemId(review.getItem().getItemId())
                        .nickName(review.getMember().getNickName())
                        .build())
                .collect(Collectors.toList());
    }
}

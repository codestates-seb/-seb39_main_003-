package com.web.MyPetForApp.wish.controller;

import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.wish.dto.WishDto;
import com.web.MyPetForApp.wish.mapper.WishMapper;
import com.web.MyPetForApp.wish.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "좋아요 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wish")
public class WishController {
    private final WishService wishService;
    private final WishMapper mapper;

    @Operation(summary = "좋아요 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping
    public ResponseEntity getWish(@RequestBody WishDto.Post requestBody){
        String memberId = requestBody.getMemberId();
        String itemId = requestBody.getItemId();
        WishDto.Response response = wishService.findWish(memberId, itemId);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @Operation(summary = "좋아요 누르기")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping
    public ResponseEntity postWish(@RequestBody WishDto.Post requestBody){
        String memberId = requestBody.getMemberId();
        String itemId = requestBody.getItemId();
        WishDto.Response response = wishService.changeWish(memberId, itemId);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

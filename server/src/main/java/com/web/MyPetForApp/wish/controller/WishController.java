package com.web.MyPetForApp.wish.controller;

import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.wish.dto.WishDto;
import com.web.MyPetForApp.wish.entity.Wish;
import com.web.MyPetForApp.wish.mapper.WishMapper;
import com.web.MyPetForApp.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wish")
public class WishController {
    private final WishService wishService;
    private final WishMapper mapper;
    @GetMapping
    public ResponseEntity getWish(@RequestBody WishDto.Post requestBody){
        Long memberId = requestBody.getMemberId();
        Long itemId = requestBody.getItemId();
        WishDto.Response response = wishService.findWish(memberId, itemId);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postWish(@RequestBody WishDto.Post requestBody){
        Long memberId = requestBody.getMemberId();
        Long itemId = requestBody.getItemId();
        WishDto.Response response = wishService.changeWish(memberId, itemId);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}

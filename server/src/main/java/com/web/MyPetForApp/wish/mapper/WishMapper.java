package com.web.MyPetForApp.wish.mapper;

import com.web.MyPetForApp.wish.dto.WishDto;
import com.web.MyPetForApp.wish.entity.Wish;
import org.springframework.stereotype.Component;

@Component
public class WishMapper {
    public WishDto.Response wishToWishResponseDto(Wish wish){
        return WishDto.Response.builder()
                .wishCnt(wish.getItem().getWishes().size())
                .build();
    }
}

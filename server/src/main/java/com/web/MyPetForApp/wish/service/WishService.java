package com.web.MyPetForApp.wish.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.item.service.ItemService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import com.web.MyPetForApp.member.service.MemberService;
import com.web.MyPetForApp.wish.dto.WishDto;
import com.web.MyPetForApp.wish.entity.Wish;
import com.web.MyPetForApp.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    public WishDto.Response changeWish(Long memberId, Long itemId){
        Member member = memberService.findVerifiedMember(memberId);
        Item item = itemService.findVerifiedItem(itemId);
        Optional<Wish> optionalWish = wishRepository.findByMemberAndItem(member, item);

        if(optionalWish.isEmpty()){ // Member와 Item로 찾은 Wish가 없으면 새로 생성 후 true 반환
            Wish createdWish = new Wish();
            createdWish.changeItem(item);
            createdWish.changeMember(member);
            item.updateWishCnt();
            wishRepository.save(createdWish);
            return createWishResponseDto(item.getWishCnt(), true);
        } else {    // 있으면 삭제 후 false 반환
            Wish findWish = optionalWish.get();
            item.getWishes().removeIf(wish -> wish.getWishId().equals(findWish.getWishId()));
            member.getWishes().removeIf(wish -> wish.getWishId().equals(findWish.getWishId()));
            wishRepository.delete(findWish);
            item.updateWishCnt();
            return createWishResponseDto(item.getWishCnt(), false);
        }
    }

    public WishDto.Response findWish(Long memberId, Long itemId){
        Member member = memberService.findVerifiedMember(memberId);
        Item item = itemService.findVerifiedItem(itemId);
        Optional<Wish> optionalWish = wishRepository.findByMemberAndItem(member, item);
        if(optionalWish.isEmpty()){ // 없으면 false 반환
            return createWishResponseDto(item.getWishCnt(), false);
        } else {    // 있으면 true반환
            return createWishResponseDto(item.getWishCnt(), true);
        }
    }

    public WishDto.Response createWishResponseDto(int wishCnt, boolean isWished){
        return WishDto.Response.builder()
                .wishCnt(wishCnt)
                .isWished(isWished)
                .build();
    }
}

package com.web.MyPetForApp.wish.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import com.web.MyPetForApp.wish.dto.WishDto;
import com.web.MyPetForApp.wish.entity.Wish;
import com.web.MyPetForApp.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public WishDto.Response changeWish(Long memberId, Long itemId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        Optional<Wish> optionalWish = wishRepository.findByMemberAndItem(member, item);

        if(optionalWish.isEmpty()){ // memberId와 itemId로 찾은 Wish가 없으면 새로 생성
            Wish createdWish = new Wish();
            createdWish.setItem(item);
            createdWish.setMember(member);
            item.updateWishCnt();
            wishRepository.save(createdWish);
            return WishDto.Response.builder()
                    .wishCnt(item.getWishCnt())
                    .isWished(true)
                    .build();
        } else {    // 이미 있으면 삭제
            Wish findWish = optionalWish.get();
            item.getWishes().removeIf(wish -> wish.getWishId().equals(findWish.getWishId()));
            member.getWishes().removeIf(wish -> wish.getWishId().equals(findWish.getWishId()));
            wishRepository.delete(findWish);
            item.updateWishCnt();
            return WishDto.Response.builder()
                    .wishCnt(item.getWishCnt())
                    .isWished(false)
                    .build();
        }
    }

    public WishDto.Response findWish(Long memberId, Long itemId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        Optional<Wish> optionalWish = wishRepository.findByMemberAndItem(member, item);
        if(optionalWish.isEmpty()){ // 없으면 wishCnt와 false 반환
            return WishDto.Response.builder()
                    .wishCnt(item.getWishCnt())
                    .isWished(false)
                    .build();
        } else {    // 있으면 wishCnt와 true반환
            return WishDto.Response.builder()
                    .wishCnt(item.getWishCnt())
                    .isWished(true)
                    .build();
        }
    }
}

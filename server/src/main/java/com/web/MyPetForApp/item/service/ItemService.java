package com.web.MyPetForApp.item.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.entity.ItemCategory;
import com.web.MyPetForApp.item.repository.ItemCategoryRepository;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import com.web.MyPetForApp.wish.entity.Wish;
import com.web.MyPetForApp.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final WishRepository wishRepository;

    public Item createItem(Item item, Long memberId, Long itemCategoryId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        ItemCategory itemCategory = itemCategoryRepository.findById(itemCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("상품 카테고리가 존재하지 않습니다."));
        item.setMember(member);
        item.setItemCategory(itemCategory);
        return itemRepository.save(item);
    }

    public Item findItem(Long itemId){
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    public Page<Item> findItems(Long itemCategoryId, int page, int size, String sortBy){
        ItemCategory itemCategory = itemCategoryRepository.findById(itemCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("상품 카테고리가 존재하지 않습니다."));
        return itemRepository.findAllByItemCategory(itemCategory, PageRequest.of(page, size,
                Sort.by(sortBy).descending()));
    }

    public Page<Item> findWishItems(Long memberId, int page, int size){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        List<Wish> findWishes = wishRepository.findAllByMember(member);
        List<Item> wishItems = findWishes
                .stream()
                .map(wish -> wish.getItem())
                .collect(Collectors.toList());
        // List<Item> to Page<Item>
        PageRequest pageable = PageRequest.of(page, size, Sort.by("wishId").ascending());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), wishItems.size());
        if(start>wishItems.size())
            return new PageImpl<>(new ArrayList<>(), pageable, wishItems.size());
        return new PageImpl<>(wishItems.subList(start, end), pageable, wishItems.size());
    }

    public Item updateItem(Long itemId, Item item){
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        findItem.update(item);

        return findItem;
    }
    public void deleteItem(Long itemId){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        itemRepository.delete(item);
    }
}

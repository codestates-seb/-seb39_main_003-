package com.web.MyPetForApp.item.service;

import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.entity.ItemCategory;
import com.web.MyPetForApp.item.repository.ItemCategoryRepository;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.service.MemberService;
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
    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final WishRepository wishRepository;
    private final MemberService memberService;
    private final ImageService imageService;

    public Item createItem(Item item, String memberId, Long itemCategoryId){
        Member member = memberService.findVerifiedMember(memberId);
//        for (String image : itemImages) {
//            ItemImage itemImage = ItemImage.builder()
//                    .itemThumbnail(image)
//                    .build();
//            ItemImage savedItemImage = itemImageRepository.save(itemImage);
//            item.addItemImage(savedItemImage);
//        }
        ItemCategory itemCategory = findVerifiedItemCategory(itemCategoryId);
        item.changeMember(member);
        item.changeItemCategory(itemCategory);
        return itemRepository.save(item);
    }

    public Item findItem(String itemId){
        return findVerifiedItem(itemId);
    }

    public Page<Item> findItems(Long itemCategoryId, int page, int size, String sortBy){
        ItemCategory itemCategory = findVerifiedItemCategory(itemCategoryId);
        return itemRepository.findAllByItemCategory(itemCategory, PageRequest.of(page, size,
                Sort.by(sortBy).descending()));
    }

    public Page<Item> findWishItems(String memberId, int page, int size){
        Member member = memberService.findVerifiedMember(memberId);
        List<Wish> findWishes = wishRepository.findAllByMember(member);
        List<Item> wishItems = findWishes
                .stream()
                .map(wish -> wish.getItem())
                .collect(Collectors.toList());
        return listToPage(page, size, wishItems);
    }

    public Item updateItem(String itemId, Item item, List<String> itemImages){
        Item findItem = findVerifiedItem(itemId);
//        if(itemImages != null) {
            // itemImage
//            findItem.resetItemImages();
//            itemImageRepository.deleteByItem(findItem);
//            for (String image : itemImages) {
//                ItemImage itemImage = ItemImage.builder()
//                        .itemThumbnail(image)
//                        .build();
//                itemImage.changeItem(findItem);
//                itemImageRepository.save(itemImage);
//            }
//        }
        findItem.updateItem(item);

        return findItem;
    }

    public void deleteItem(String itemId){
        Item item = findVerifiedItem(itemId);
        // 상품에 등록되있던 이미지 전체 삭제
        List<String> fileNameList = imageService.findFilesById("item", itemId);
        fileNameList.forEach(file ->
                imageService.deleteFile(file, "item"));

        itemRepository.delete(item);
    }

    // List<Item> 에서 Page<Item>으로 변환
    public PageImpl<Item> listToPage(int page, int size, List<Item> wishItems) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("wishId").ascending());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), wishItems.size());
        if(start> wishItems.size())
            return new PageImpl<>(new ArrayList<>(), pageable, wishItems.size());
        return new PageImpl<>(wishItems.subList(start, end), pageable, wishItems.size());
    }
    public Item findVerifiedItem(String itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    public ItemCategory findVerifiedItemCategory(Long itemCategoryId) {
        return itemCategoryRepository.findById(itemCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("상품 카테고리가 존재하지 않습니다."));
    }

    public void checkStockCnt(int itemCnt, Item item){
        if(item.getStockCnt()<itemCnt){
            throw new IllegalArgumentException(item.getItemName()+"의 재고량이 부족합니다.");
        }
    }

    public String createItemId(Long itemCategoryId) {

        Item lastItem = itemRepository.findFirstByOrderByItemIdDesc().orElse(null);
        long identity = lastItem != null ? Long.parseLong(lastItem.getItemId().substring(1,2)) + 1 : 1L;

//        long identity = itemRepository.count() != 0 ? itemRepository.count() + 1 : 1L;
        return "I" + identity + "CTG" + itemCategoryId;
    }
}
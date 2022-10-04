package com.web.MyPetForApp.item.service;

import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final WishRepository wishRepository;
    private final MemberService memberService;
    private final ImageService imageService;
    private final String baseItemImgUrl = "https://mypet-image.s3.ap-northeast-2.amazonaws.com/items/";

    public Item createItem(Item item, String memberId, String itemCategoryId, List<MultipartFile> mainImg, List<MultipartFile> detailImg){
        Member member = memberService.findVerifiedMember(memberId);
        ItemCategory itemCategory = findVerifiedItemCategory(itemCategoryId);
        item.changeMember(member);
        item.changeItemCategory(itemCategory);
        Item savedItem = itemRepository.save(item);

        String thumbnail = null;
        if (mainImg != null) {
            thumbnail = baseItemImgUrl + imageService.uploadFile(mainImg, "item", item.getItemId(), "main").get(0);
        }

        if (detailImg != null) {
            imageService.uploadFile(detailImg, "item", item.getItemId(), "detail");
        }
        savedItem.changeThumbnail(thumbnail);
        return savedItem;
    }

    public Item findItem(String itemId){
        return findVerifiedItem(itemId);
    }

    public Page<Item> findItems(String itemCategoryId, int page, int size, String sortBy){
        findVerifiedItemCategory(itemCategoryId);

        return itemRepository.findAllByItemCategoryItemCategoryIdStartingWith(itemCategoryId, PageRequest.of(page, size,
                Sort.by(sortBy).descending()));
    }

    public Page<Item> findWishItems(String memberId, int page, int size){
        Member member = memberService.findVerifiedMember(memberId);
        List<Wish> findWishes = wishRepository.findAllByMember(member);
        return itemRepository.findAllByWishesIn(findWishes, PageRequest.of(page, size,
                Sort.by("wishCnt").descending()));
    }

    public Item updateItem(String itemId, Item item, String memberId){
        Item findItem = findVerifiedItem(itemId);
        if(!memberId.equals(findItem.getMember().getMemberId())){
            throw new BusinessLogicException(ExceptionCode.ITEM_NOT_FOUND);
        }
        findItem.updateItem(item);

        return findItem;
    }

    public void deleteItem(String itemId){
        Item item = findVerifiedItem(itemId);
        // 상품에 등록되있던 이미지 전체 삭제
        List<String> fileNameList = imageService.findFilesById("item", itemId);
        if(fileNameList.size() > 0) {
            fileNameList.forEach(file ->
                    imageService.deleteFile(file.substring(file.lastIndexOf("/") + 1), "item", itemId));
        }
        itemRepository.delete(item);
    }

    public Page<Item> searchItem(String word, int page, int size, String sortBy){
        return itemRepository.findAllByWord(word, PageRequest.of(page, size,
                Sort.by(sortBy).descending()));
    }

    public Item findVerifiedItem(String itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ITEM_NOT_FOUND));
    }

    public ItemCategory findVerifiedItemCategory(String itemCategoryId) {
        return itemCategoryRepository.findByItemCategoryId(itemCategoryId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ITEM_CATEGORY_NOT_FOUND));
    }

    public void checkStockCnt(int itemCnt, Item item){
        if(item.getStockCnt()<itemCnt){
            throw new BusinessLogicException(ExceptionCode.OUT_OF_STOCK);
        }
    }

    public String createItemId(Long itemCategoryId) {

        Item lastItem = itemRepository.findFirstByOrderByItemIdDesc().orElse(null);
        long identity = lastItem != null ? Long.parseLong(lastItem.getItemId().substring(1,2)) + 1 : 1L;

//        long identity = itemRepository.count() != 0 ? itemRepository.count() + 1 : 1L;
        return "I" + identity + "CTG" + itemCategoryId;
    }
}

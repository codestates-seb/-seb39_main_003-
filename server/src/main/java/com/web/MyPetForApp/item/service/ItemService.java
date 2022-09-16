package com.web.MyPetForApp.item.service;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.entity.ItemCategory;
import com.web.MyPetForApp.item.repository.ItemCategoryRepository;
import com.web.MyPetForApp.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    public Item createItem(Item item){
        Item savedItem = itemRepository.save(item);
        return savedItem;
    }
    public Item findItem(Long itemId){
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        return findItem;
    }
    public Page<Item> findItems(Long itemCategoryId, int page){
        ItemCategory itemCategory = itemCategoryRepository.findById(itemCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리 항목입니다."));
        return itemRepository.findAllByItemCategory(itemCategory, PageRequest.of(page, 20,
                Sort.by("soldCnt").descending()));

    }
    public Item updateItem(Long itemId, Item item){
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        Item findItem = optionalItem.orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        // 수정할 값이 초기값이 아닌 채로 들어오면 수정
        Optional.ofNullable(item.getImage()).ifPresent(image -> findItem.setImage(image));
        Optional.ofNullable(item.getItemName()).ifPresent(itemName -> findItem.setItemName(itemName));
        Optional.ofNullable(item.getInfo()).ifPresent(info -> findItem.setInfo(info));
        if(item.getPrice() != 0) findItem.setPrice(item.getPrice());
        if(item.getStockCnt() != 0) findItem.setStockCnt(item.getStockCnt());

        return findItem;
    }
    public void deleteItem(Long itemId){
        itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        itemRepository.deleteById(itemId);
    }
}

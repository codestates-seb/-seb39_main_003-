package com.web.MyPetForApp.item.controller;

import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.item.dto.ItemDto;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.mapper.ItemMapper;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    private final ItemMapper mapper;

    @PostMapping
    public ResponseEntity postItem(@RequestBody ItemDto.Post requestBody){
        Item item = itemService.createItem(mapper.itemPostDtoToItem(requestBody),
                requestBody.getMemberId(),
                requestBody.getItemCategoryId(),
                requestBody.getItemImages());
        ItemDto.Response response = mapper.itemToItemResponseDto(item);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getItem(@PathVariable Long itemId){
        Item item = itemService.findItem(itemId);
        ItemDto.Response response = mapper.itemToItemResponseDto(item);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    // 정렬 기준의 기본 값은 우선 판매량(soldCnt) 순으로 해놓았고 추후 변경 예정
    // 지금은 입력받는 정렬 기준에 따라 내림차순으로 정렬이 되도록 변경 (price, soldCnt, wishCnt 등)
    @GetMapping
    public ResponseEntity getItems(@RequestParam Long itemCategoryId,
                                   @RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "8") int size,
                                   @RequestParam(required = false, defaultValue = "soldCnt") String sortBy){
        Page<Item> pageItems = itemService.findItems(itemCategoryId, page-1, size, sortBy);
        List<Item> items = pageItems.getContent();
        List<ItemDto.Response> response = mapper.itemsToItemResponseDto(items);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageItems), HttpStatus.OK);
    }
    @GetMapping("/wish")
    public ResponseEntity getWishItems(@RequestParam Long memberId,
                                       @RequestParam(required = false, defaultValue = "1") int page,
                                       @RequestParam(required = false, defaultValue = "8") int size){
        Page<Item> pageWishItems = itemService.findWishItems(memberId, page-1, size);
        List<Item> items = pageWishItems.getContent();
        List<ItemDto.Response> response = mapper.itemsToItemResponseDto(items);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageWishItems), HttpStatus.OK);
    }


    @PatchMapping("/{itemId}")
    public ResponseEntity patchItem(@PathVariable Long itemId,
                                    @RequestBody ItemDto.Patch requestBody){
        Item item = itemService.updateItem(itemId, mapper.itemPatchDtoToItem(requestBody), requestBody.getItemImages());
        ItemDto.Response response = mapper.itemToItemResponseDto(item);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

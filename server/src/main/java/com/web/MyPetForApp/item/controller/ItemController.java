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
        Item item = itemService.createItem(mapper.itemPostDtoToItem(requestBody));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getItem(@PathVariable Long itemId){
        Item item = itemService.findItem(itemId);
        ItemDto.Response response = mapper.itemToItemResponseDto(item);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{itemCategoryId}")
    public ResponseEntity getItems(@PathVariable Long itemCategoryId,
                                   @RequestParam(required = false, defaultValue = "1") int page){
        Page<Item> pageItems = itemService.findItems(itemCategoryId, page);
        List<Item> items = pageItems.getContent();
        List<ItemDto.Response> response = mapper.itemsToItemResponseDto(items);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageItems), HttpStatus.OK);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity patchItem(@PathVariable Long itemId,
                                    @RequestBody ItemDto.Patch requestBody){
        Item item = itemService.updateItem(itemId, mapper.itemPatchDtoToItem(requestBody));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

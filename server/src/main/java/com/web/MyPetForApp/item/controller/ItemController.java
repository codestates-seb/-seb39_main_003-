package com.web.MyPetForApp.item.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.MyPetForApp.util.StringIdGenerator;
import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.item.dto.ItemDto;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.mapper.ItemMapper;
import com.web.MyPetForApp.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;
    private final ImageService imageService;
    private final ItemMapper mapper;
    private final StringIdGenerator stringIdGenerator;

    @Operation(summary = "상품 등록")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity postItem(@ParameterObject @ModelAttribute ItemDto.Post requestBody,
                                   @RequestPart(required = false) List<MultipartFile> mainImg,
                                   @RequestPart(required = false) List<MultipartFile> detailImg){
        String itemId = stringIdGenerator.createItemId();
        Item item = itemService.createItem(mapper.itemPostDtoToItem(requestBody, itemId),
                requestBody.getMemberId(),
                requestBody.getItemCategoryId());

        List<String> fileNameList = new ArrayList<>();

        if(mainImg != null) {
           fileNameList.add(imageService.uploadFile(mainImg, "item", itemId, "main").get(0));
        }

        if(detailImg != null) {
            fileNameList.addAll(imageService.uploadFile(detailImg, "item", itemId, "detail"));
        }

        ItemDto.Response response = mapper.itemToItemResponseDto(item,fileNameList);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @Operation(summary = "하나의 상품 정보 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/{itemId}")
    public ResponseEntity getItem(@PathVariable String itemId){
        Item item = itemService.findItem(itemId);
        List<String> fileNameList = imageService.findFilesById("item" ,itemId);
        ItemDto.Response response = mapper.itemToItemResponseDto(item, fileNameList);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @Operation(summary = "상품 리스트 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    // 정렬 기준의 기본 값은 우선 판매량(soldCnt) 순으로 해놓았고 추후 변경 예정
    // 지금은 입력받는 정렬 기준에 따라 내림차순으로 정렬이 되도록 변경 (price, soldCnt, wishCnt 등)
    @GetMapping
    public ResponseEntity getItems(@Parameter(description = "카테고리 식별번호") @RequestParam String itemCategoryId,
                                   @Parameter(description = "현재 페이지") @RequestParam(required = false, defaultValue = "1") int page,
                                   @Parameter(description = "한 페이지 당 상품 수") @RequestParam(required = false, defaultValue = "8") int size,
                                   @Parameter(description = "정렬 기준, 기본값 = 판매량") @RequestParam(required = false, defaultValue = "soldCnt") String sortBy){
        Page<Item> pageItems = itemService.findItems(itemCategoryId, page-1, size, sortBy);
        List<Item> items = pageItems.getContent();
        List<ItemDto.Response> response = mapper.itemsToItemResponseDto(items);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageItems), HttpStatus.OK);
    }
    @Operation(summary = "좋아요 등록한 상품 리스트 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/wish")
    public ResponseEntity getWishItems(@Parameter(description = "회원 식별번호") @RequestParam String memberId,
                                       @Parameter(description = "현재 페이지") @RequestParam(required = false, defaultValue = "1") int page,
                                       @Parameter(description = "한 페이지 당 상품 수") @RequestParam(required = false, defaultValue = "8") int size){
        Page<Item> pageWishItems = itemService.findWishItems(memberId, page-1, size);
        List<Item> items = pageWishItems.getContent();
        List<ItemDto.Response> response = mapper.itemsToItemResponseDto(items);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageWishItems), HttpStatus.OK);
    }
    @Operation(summary = "상품 정보 수정")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping("/{itemId}")
    public ResponseEntity patchItem(@PathVariable String itemId,
                                    @RequestBody ItemDto.Patch requestBody){
        Item item = itemService.updateItem(itemId, mapper.itemPatchDtoToItem(requestBody));
        ItemDto.Response response = mapper.itemToItemResponseDto(item, null);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Operation(summary = "회원탈퇴")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    )
    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable String itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

package com.web.MyPetForApp.item.mapper;

import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.item.dto.ItemDto;
import com.web.MyPetForApp.item.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    private final ImageService imageService;

    public Item itemPostDtoToItem(ItemDto.Post requestBody, String itemId){
        return Item.builder()
                .itemId(itemId)
                .itemName(requestBody.getItemName())
                .price(requestBody.getPrice())
                .stockCnt(requestBody.getStockCnt())
                .info(requestBody.getInfo())
                .build();
    }

    public Item itemPatchDtoToItem(ItemDto.Patch requestBody){
        return Item.builder()
                .itemName(requestBody.getItemName())
                .price(requestBody.getPrice())
                .stockCnt(requestBody.getStockCnt())
                .info(requestBody.getInfo())
                .build();
    }

    public ItemDto.Response itemToItemResponseDto(Item item){
        return ItemDto.Response.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .soldCnt(item.getSoldCnt())
                .stockCnt(item.getStockCnt())
                .info(item.getInfo())
                .wishCnt(item.getWishCnt())   // 찜 수
                .itemCategory(item.getItemCategory().getItemCategory()) // 카테고리 명
                .nickName(item.getMember().getNickName()) // 닉네임? 실명? 선택 필요
                .thumbnail(item.getThumbnail())
                .build();

    }

    public List<ItemDto.Response> itemsToItemResponseDto(List<Item> items){
        return items
                .stream()
                .map(item -> ItemDto.Response
                        .builder()
                        .itemId(item.getItemId())
                        .itemName(item.getItemName())
                        .price(item.getPrice())
                        .soldCnt(item.getSoldCnt())
                        .stockCnt(item.getStockCnt())
                        .info(item.getInfo())
                        .wishCnt(item.getWishCnt())
                        .itemCategory(item.getItemCategory().getItemCategory())
                        .nickName(item.getMember().getNickName())
                        .thumbnail(item.getThumbnail())
                        .build())
                .collect(Collectors.toList());
    }
}

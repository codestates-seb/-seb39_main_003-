package com.web.MyPetForApp.item.mapper;

import com.web.MyPetForApp.item.dto.ItemDto;
import com.web.MyPetForApp.item.entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {
    public Item itemPostDtoToItem(ItemDto.Post requestBody){
        return Item.builder()
                .image(requestBody.getImage())
                .itemName(requestBody.getItemName())
                .price(requestBody.getPrice())
                .stockCnt(requestBody.getStockCnt())
                .info(requestBody.getInfo())
                .build();
    }

    public Item itemPatchDtoToItem(ItemDto.Patch requestBody){
        return Item.builder()
                .image(requestBody.getImage())
                .itemName(requestBody.getItemName())
                .price(requestBody.getPrice())
                .stockCnt(requestBody.getStockCnt())
                .info(requestBody.getInfo())
                .build();
    }

    public ItemDto.Response itemToItemResponseDto(Item item){
        return ItemDto.Response.builder()
                .image(item.getImage())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .soldCnt(item.getSoldCnt())
                .stockCnt(item.getStockCnt())
                .info(item.getInfo())
                .wishCnt(item.getWishes().size())   // 찜 수
                .itemCategory(item.getItemCategory().getItemCategory()) // 카테고리 명
                .member(item.getMember().getNickName()) // 닉네임? 실명? 선택 필요
                .build();

    }

    public List<ItemDto.Response> itemsToItemResponseDto(List<Item> items){
        return items
                .stream()
                .map(item -> ItemDto.Response
                        .builder()
                        .image(item.getImage())
                        .itemName(item.getItemName())
                        .price(item.getPrice())
                        .soldCnt(item.getSoldCnt())
                        .stockCnt(item.getStockCnt())
                        .info(item.getInfo())
                        .wishCnt(item.getWishes().size())
                        .itemCategory(item.getItemCategory().getItemCategory())
                        .member(item.getMember().getNickName())
                        .build())
                .collect(Collectors.toList());
    }
}

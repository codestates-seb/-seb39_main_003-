package com.web.MyPetForApp.cartitem.mapper;


import com.web.MyPetForApp.cartitem.dto.CartItemDto;
import com.web.MyPetForApp.cartitem.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemMapper {

    public CartItem cartItemPostDtoToCartItem(CartItemDto.Post requestBody){
        return CartItem.builder()
                .itemCnt(requestBody.getItemCnt())
                .build();
    }

    public CartItem cartItemPatchDtoToCartItem(CartItemDto.Patch requestBody){
        return CartItem.builder()
                .itemCnt(requestBody.getItemCnt())
                .build();
    }

    public CartItemDto.Response cartItemToCartItemResponseDto(CartItem cartItem){
        return CartItemDto.Response.builder()
                .cartItemId(cartItem.getCartItemId())
                .itemCnt(cartItem.getItemCnt())
                .price(cartItem.getItem().getPrice())
                .totalPrice(cartItem.getItemCnt() * cartItem.getItem().getPrice())
                .itemName(cartItem.getItem().getItemName())
                .thumbnail(cartItem.getItem().getThumbnail())
                .itemId(cartItem.getItem().getItemId())
                .build();
    }

    public List<CartItemDto.Response> cartItemsToCartItemResponseDto(List<CartItem> cartItems){
        return cartItems
                .stream()
                .map(cartItem -> CartItemDto.Response
                        .builder()
                        .cartItemId(cartItem.getCartItemId())
                        .itemCnt(cartItem.getItemCnt())
                        .price(cartItem.getItem().getPrice())
                        .totalPrice(cartItem.getItemCnt() * cartItem.getItem().getPrice())
                        .itemName(cartItem.getItem().getItemName())
                        .thumbnail(cartItem.getItem().getThumbnail())
                        .itemId(cartItem.getItem().getItemId())
                        .build())
                .collect(Collectors.toList());
    }
}

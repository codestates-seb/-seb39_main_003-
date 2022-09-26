package com.web.MyPetForApp.cartitem.controller;

import com.web.MyPetForApp.cartitem.dto.CartItemDto;
import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.cartitem.mapper.CartItemMapper;
import com.web.MyPetForApp.cartitem.service.CartItemService;
import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartItemController {
    private final CartItemService cartItemService;
    private final CartItemMapper mapper;

    @PostMapping
    public ResponseEntity post(@RequestBody CartItemDto.Post requestBody){
        CartItem cartItem = mapper.cartItemPostDtoToCartItem(requestBody);
        String itemId = requestBody.getItemId();
        String memberId = requestBody.getMemberId();
        CartItem savedCartItem = cartItemService.createCartItem(cartItem, memberId, itemId);
        CartItemDto.Response response = mapper.cartItemToCartItemResponseDto(savedCartItem);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity getCartItem(@PathVariable Long cartItemId){
        CartItem cartItem = cartItemService.findCartItem(cartItemId);
        CartItemDto.Response response = mapper.cartItemToCartItemResponseDto(cartItem);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCartItems(@RequestParam String memberId,
                                      @RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size){
        Page<CartItem> pageCartItems = cartItemService.findCartItems(memberId, page-1, size);
        List<CartItem> cartItems = pageCartItems.getContent();
        List<CartItemDto.Response> response = mapper.cartItemsToCartItemResponseDto(cartItems);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageCartItems), HttpStatus.OK);
    }

    @PatchMapping("/{cartItemId}")
    public ResponseEntity patchCartItem(@PathVariable Long cartItemId,
                                        @RequestBody CartItemDto.Patch requestBody){
        int itemCnt = requestBody.getItemCnt();
        String itemId = requestBody.getItemId();
        Long memberId = requestBody.getMemberId();
        CartItem cartItem = cartItemService.updateCartItem(cartItemId, itemCnt, itemId, memberId);
        CartItemDto.Response response = mapper.cartItemToCartItemResponseDto(cartItem);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity deleteCartItem(@PathVariable Long cartItemId){
        cartItemService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

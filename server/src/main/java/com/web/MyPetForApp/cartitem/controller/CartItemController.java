package com.web.MyPetForApp.cartitem.controller;

import com.web.MyPetForApp.cartitem.dto.CartItemDto;
import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.cartitem.mapper.CartItemMapper;
import com.web.MyPetForApp.cartitem.service.CartItemService;
import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Tag(name = "장바구니 API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/cart")
public class CartItemController {
    private final CartItemService cartItemService;
    private final CartItemMapper mapper;

    @Operation(summary = "장바구니 등록")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping
    public ResponseEntity post(@Valid @RequestBody CartItemDto.Post requestBody){
        CartItem cartItem = mapper.cartItemPostDtoToCartItem(requestBody);
        String itemId = requestBody.getItemId();
        String memberId = requestBody.getMemberId();
        CartItem savedCartItem = cartItemService.createCartItem(cartItem, memberId, itemId);
        CartItemDto.Response response = mapper.cartItemToCartItemResponseDto(savedCartItem);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @Operation(summary = "한 장바구니 정보 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/{cartItemId}")
    public ResponseEntity getCartItem(@Positive @PathVariable Long cartItemId){
        CartItem cartItem = cartItemService.findCartItem(cartItemId);
        CartItemDto.Response response = mapper.cartItemToCartItemResponseDto(cartItem);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Operation(summary = "장바구니 리스트 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping
    public ResponseEntity getCartItems(@Parameter(description = "회원 식별번호") @NotBlank @RequestParam String memberId,
                                       @Parameter(description = "현재 페이지") @Positive @RequestParam(required = false, defaultValue = "1") int page,
                                       @Parameter(description = "한 페이지에 담길 상품 수") @Positive @RequestParam(required = false, defaultValue = "10") int size){
        Page<CartItem> pageCartItems = cartItemService.findCartItems(memberId, page-1, size);
        List<CartItem> cartItems = pageCartItems.getContent();
        List<CartItemDto.Response> response = mapper.cartItemsToCartItemResponseDto(cartItems);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageCartItems), HttpStatus.OK);
    }
    @Operation(summary = "장바구니 정보 수정")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping("/{cartItemId}")
    public ResponseEntity patchCartItem(@Positive @PathVariable Long cartItemId,
                                        @Valid @RequestBody CartItemDto.Patch requestBody){
        int itemCnt = requestBody.getItemCnt();
        String itemId = requestBody.getItemId();
        String memberId = requestBody.getMemberId();
        CartItem cartItem = cartItemService.updateCartItem(cartItemId, itemCnt, itemId, memberId);
        CartItemDto.Response response = mapper.cartItemToCartItemResponseDto(cartItem);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @Operation(summary = "장바구니 삭제")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "OK"
            )
    )
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity deleteCartItem(@Positive @PathVariable Long cartItemId){
        cartItemService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

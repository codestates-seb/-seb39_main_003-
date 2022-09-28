package com.web.MyPetForApp.cartitem.service;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.cartitem.repository.CartItemRepository;
import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.service.ItemService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.service.MemberService;
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
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ItemService itemService;
    private final MemberService memberService;
    public CartItem createCartItem(CartItem cartItem, String memberId, String itemId){
        Member member = memberService.findVerifiedMember(memberId);
        Item item = itemService.findVerifiedItem(itemId);
        verifyExistsCartItem(member, item);
        itemService.checkStockCnt(cartItem.getItemCnt(), item);
        cartItem.changeMember(member);
        cartItem.changeItem(item);
        return cartItemRepository.save(cartItem);
    }

    public CartItem findCartItem(Long cartItemId){
        return findVerifiedCartItem(cartItemId);
    }

    public Page<CartItem> findCartItems(String memberId, int page, int size){
        Member member = memberService.findVerifiedMember(memberId);
        return cartItemRepository.findAllByMember(member, PageRequest.of(page, size,
                Sort.by("cartItemId").ascending()));
    }

    public CartItem updateCartItem(Long cartItemId, int itemCnt, String itemId, Long memberId){
        CartItem findCartItem = findVerifiedCartItem(cartItemId);
        Item item = itemService.findVerifiedItem(itemId);
        if(!memberId.equals(findCartItem.getMember().getMemberId())){
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_CART_ITEM);
        }
        itemService.checkStockCnt(itemCnt, item);
        findCartItem.updateCnt(itemCnt);
        return findCartItem;
    }

    public void deleteCartItem(Long cartItemId){
        CartItem cartItem = findVerifiedCartItem(cartItemId);
        cartItemRepository.delete(cartItem);
    }

    public CartItem findVerifiedCartItem(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CART_ITEM_NOT_FOUND));
    }

    public void verifyExistsCartItem(Member member, Item item) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findByMemberAndItem(member, item);
        if(optionalCartItem.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.CART_ITEM_EXISTS);
        }
    }
}

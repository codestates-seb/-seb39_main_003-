package com.web.MyPetForApp.cartitem.service;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.cartitem.repository.CartItemRepository;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.item.service.ItemService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import com.web.MyPetForApp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ItemService itemService;
    private final MemberService memberService;
    public CartItem createCartItem(CartItem cartItem, Long memberId, Long itemId){
        Member member = memberService.findVerifiedMember(memberId);
        Item item = itemService.findVerifiedItem(itemId);
        if(item.getStockCnt()<cartItem.getItemCnt()){
            throw new IllegalArgumentException("상품 재고가 부족합니다.");
        }
        cartItem.changeMember(member);
        cartItem.changeItem(item);
        return cartItemRepository.save(cartItem);
    }

    public CartItem findCartItem(Long cartItemId){
        return findVerifiedCartItem(cartItemId);
    }

    public Page<CartItem> findCartItems(Long memberId, int page, int size){
        Member member = memberService.findVerifiedMember(memberId);
        return cartItemRepository.findAllByMember(member, PageRequest.of(page, size,
                Sort.by("cartItemId").ascending()));
    }

    public CartItem updateCartItem(Long cartItemId, int itemCnt, Long itemId, Long memberId){
        CartItem findCartItem = findVerifiedCartItem(cartItemId);
        Item item = itemService.findVerifiedItem(itemId);
        if(memberId.equals(findCartItem.getMember().getMemberId())){
            throw new IllegalArgumentException("해당 장바구니에 대한 회원만 수정할 수 있습니다.");
        }
        if(item.getStockCnt()<itemCnt){
            throw new IllegalArgumentException("상품 재고가 부족합니다.");
        }
        findCartItem.updateCnt(itemCnt);
        return findCartItem;
    }

    public void deleteCartItem(Long cartItemId){
        CartItem cartItem = findVerifiedCartItem(cartItemId);
        cartItemRepository.delete(cartItem);
    }

    public CartItem findVerifiedCartItem(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 항목이 존재하지 않습니다."));
    }
}

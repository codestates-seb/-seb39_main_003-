package com.web.MyPetForApp.cartitem.service;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.cartitem.repository.CartItemRepository;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.repository.ItemRepository;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    public CartItem createCartItem(CartItem cartItem, Long memberId, Long itemId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        if(item.getStockCnt()<cartItem.getItemCnt()){
            throw new IllegalArgumentException("상품 재고가 부족합니다.");
        }
        cartItem.setMember(member);
        cartItem.setItem(item);
        return cartItemRepository.save(cartItem);
    }

    public CartItem findCartItem(Long cartItemId){
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 항목이 존재하지 않습니다."));
    }

    public Page<CartItem> findCartItems(Long memberId, int page, int size){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        return cartItemRepository.findAllByMember(member, PageRequest.of(page, size,
                Sort.by("cartItemId").ascending()));
    }

    public CartItem updateCartItem(Long cartItemId, int itemCnt, Long itemId, Long memberId){
        CartItem findCartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 항목이 존재하지 않습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        if(findCartItem.getMember().getMemberId() != memberId){
            throw new IllegalArgumentException("해당 장바구니에 대한 회원만 수정할 수 있습니다.");
        }
        if(item.getStockCnt()<itemCnt){
            throw new IllegalArgumentException("상품 재고가 부족합니다.");
        }
        findCartItem.changeCnt(itemCnt);
        return findCartItem;
    }

    public void deleteCartItem(Long cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 항목이 존재하지 않습니다."));
        cartItemRepository.delete(cartItem);
    }
}

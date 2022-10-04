package com.web.MyPetForApp.cartitem.repository;

import com.web.MyPetForApp.cartitem.entity.CartItem;
import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Page<CartItem> findAllByMember(Member member, Pageable pageable);
    Optional<CartItem> findByMemberAndItem(Member member, Item item);
}

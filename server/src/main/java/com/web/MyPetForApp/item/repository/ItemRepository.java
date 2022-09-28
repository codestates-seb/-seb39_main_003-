package com.web.MyPetForApp.item.repository;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.entity.ItemCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Page<Item> findAllByItemCategoryItemCategoryIdStartingWith(String itemCategoryId, Pageable pageable);
    public Optional<Item> findFirstByOrderByItemIdDesc();
    public boolean existsItemByItemId(String itemId);
}

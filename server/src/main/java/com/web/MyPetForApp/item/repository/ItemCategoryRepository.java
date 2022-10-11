package com.web.MyPetForApp.item.repository;

import com.web.MyPetForApp.item.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    Optional<ItemCategory> findByItemCategoryId(String itemCategoryId);
}

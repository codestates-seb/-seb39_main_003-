package com.web.MyPetForApp.item.repository;

import com.web.MyPetForApp.item.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
}

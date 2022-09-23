package com.web.MyPetForApp.item.repository;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
    List<ItemImage> findAllByItem(Item item);
    void deleteByItem(Item item);
}

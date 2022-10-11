package com.web.MyPetForApp.item.repository;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.item.entity.ItemCategory;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.wish.entity.Wish;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    @EntityGraph(attributePaths = {"member", "itemCategory"})
    Page<Item> findAllByItemCategoryItemCategoryIdStartingWith(String itemCategoryId, Pageable pageable);
    @EntityGraph(attributePaths = {"member", "itemCategory"})
    @Query(
            value = "SELECT i FROM Item i WHERE i.itemName LIKE %:word% OR i.info LIKE %:word%",
            countQuery = "SELECT COUNT(i.itemId) FROM Item i WHERE i.itemName LIKE %:word% OR i.info LIKE %:word%"
    )
    Page<Item> findAllByWord(String word, Pageable pageable);
    public Optional<Item> findFirstByOrderByItemIdDesc();
    public boolean existsItemByItemId(String itemId);
    @EntityGraph(attributePaths = "itemCategory")
    Page<Item> findAllByWishesIn(List<Wish> wishes, Pageable pageable);
}

package com.web.MyPetForApp.review.repository;

import com.web.MyPetForApp.item.entity.Item;
import com.web.MyPetForApp.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByItem(Item item, Pageable pageable);
}

package com.web.MyPetForApp.order.repository;

import com.web.MyPetForApp.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

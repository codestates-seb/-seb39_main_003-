package com.web.MyPetForApp.order.controller;

import com.web.MyPetForApp.dto.MultiResponseDto;
import com.web.MyPetForApp.dto.SingleResponseDto;
import com.web.MyPetForApp.order.dto.OrderDto;
import com.web.MyPetForApp.order.dto.OrderItemDto;
import com.web.MyPetForApp.order.entity.Order;
import com.web.MyPetForApp.order.mapper.OrderMapper;
import com.web.MyPetForApp.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderMapper mapper;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity postOrder(@RequestBody OrderDto.Post requestBody){
        List<OrderItemDto.Post> orderItems = requestBody.getOrderItems();
        String memberId = requestBody.getMemberId();
        Order savedOrder = orderService.createOrder(orderItems, memberId);
        OrderDto.Response response = mapper.orderToOrderResponseDto(savedOrder);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(@PathVariable Long orderId){
        Order order = orderService.findOrder(orderId);
        OrderDto.Response response = mapper.orderToOrderResponseDto(order);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(@RequestParam String memberId,
                                    @RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size){
        Page<Order> pageOrders = orderService.findOrders(memberId, page-1, size);
        List<Order> orders = pageOrders.getContent();
        List<OrderDto.Response> response = mapper.ordersToOrderResponseDto(orders);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageOrders), HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity patchOrder(@PathVariable Long orderId,
                                     @RequestBody OrderDto.Patch requestBody){
        Order.OrderStatus orderStatus = requestBody.getOrderStatus();
        Order order = orderService.updateOrder(orderId, orderStatus);
        OrderDto.Response response = mapper.orderToOrderResponseDto(order);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

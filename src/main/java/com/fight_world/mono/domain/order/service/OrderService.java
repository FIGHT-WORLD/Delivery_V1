package com.fight_world.mono.domain.order.service;

import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.dto.request.OrderUpdateRequestDto;
import com.fight_world.mono.domain.order.dto.response.OrderDetailResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderResponseDto;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.global.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResponseDto createOrder(UserDetailsImpl userDetails, OrderCreateRequestDto orderCreateRequestDto);

    OrderResponseDto updateOrder(UserDetailsImpl userDetails, String orderId, OrderUpdateRequestDto requestDto);

    Page<OrderResponseDto> getOrders(UserDetailsImpl userDetails, Pageable pageable,
            String store_name, String menu_name);

    OrderDetailResponseDto getOrder(UserDetailsImpl userDetails, String orderId);

    void deleteOrder(UserDetailsImpl userDetails, String orderId);

    Order findById(String orderId);

    void updateOrderToCooking(String orderId, UserDetailsImpl userDetails);

    Page<OrderResponseDto> getStoreOrders(String storeId, UserDetailsImpl userDetails, Pageable pageable, Long user_id);

//    BigDecimal getOrderTotalPrice(UserDetailsImpl userDetails, String orderId);
}

package com.fight_world.mono.domain.order.service;

import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.dto.response.OrderDetailResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderResponseDto;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResponseDto createOrder(UserDetailsImpl userDetails, OrderCreateRequestDto orderCreateRequestDto);

    Page<OrderResponseDto> getOrders(UserDetailsImpl userDetails, Pageable pageable,
            String store_name, String menu_name);

    OrderDetailResponseDto getOrder(UserDetailsImpl userDetails, String orderId);

    void deleteOrderFromUser(UserDetailsImpl userDetails, String orderId);

    Order findById(String orderId);

    void updateOrderToCooking(String orderId, UserDetailsImpl userDetails);

    Page<OrderResponseDto> getStoreOrders(String storeId, UserDetailsImpl userDetails, Pageable pageable, Long user_id);
}

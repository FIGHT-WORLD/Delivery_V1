package com.fight_world.mono.domain.order.service;

import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.dto.response.OrderDetailResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderResponseDto;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(UserDetailsImpl userDetails, OrderCreateRequestDto orderCreateRequestDto);

    List<OrderResponseDto> getOrders(UserDetailsImpl userDetails);

    OrderDetailResponseDto getOrder(UserDetailsImpl userDetails, String orderId);

    void deleteOrderFromUser(UserDetailsImpl userDetails, String orderId);

}

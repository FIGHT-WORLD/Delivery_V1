package com.fight_world.mono.domain.order.service;

import com.fight_world.mono.domain.order.dto.response.OrderDetailResponseDto;
import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.dto.response.OrderResponseDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface OrderService {

    OrderResponseDto createOrder(UserDetails userDetails, OrderCreateRequestDto orderCreateRequestDto);

    List<OrderResponseDto> getOrders(UserDetails userDetails);

    OrderDetailResponseDto getOrder(UserDetails userDetails, String orderId);

    void deleteOrder(UserDetails userDetails, String orderId);


}

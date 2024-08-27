package com.fight_world.mono.domain.order.service;

import com.fight_world.mono.domain.order.dto.response.OrderDetailResponseDto;
import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.dto.response.OrderResponseDto;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    OrderResponseDto createOrder(UserDetailsImpl userDetails, OrderCreateRequestDto orderCreateRequestDto);

    List<OrderResponseDto> getOrders(UserDetails userDetails);

    OrderDetailResponseDto getOrder(UserDetails userDetails, String orderId);

    void deleteOrder(UserDetails userDetails, String orderId);

}

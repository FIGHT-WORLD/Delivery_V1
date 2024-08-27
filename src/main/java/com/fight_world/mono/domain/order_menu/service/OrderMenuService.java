package com.fight_world.mono.domain.order_menu.service;

import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order_menu.dto.request.OrderMenuCreateRequestDto;
import java.util.List;

public interface OrderMenuService {

    void createOrderMenus(List<OrderMenuCreateRequestDto> orderMenuCreateRequestDtoList, Order order);
}

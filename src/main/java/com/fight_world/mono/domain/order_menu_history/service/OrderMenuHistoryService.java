package com.fight_world.mono.domain.order_menu_history.service;

import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order_menu_history.dto.request.OrderMenuHistoryCreateRequestDto;
import java.util.List;

public interface OrderMenuHistoryService {

    void createOrderMenuHistorys(
            List<OrderMenuHistoryCreateRequestDto> orderMenuHistoryCreateRequestDto, Order order);
}

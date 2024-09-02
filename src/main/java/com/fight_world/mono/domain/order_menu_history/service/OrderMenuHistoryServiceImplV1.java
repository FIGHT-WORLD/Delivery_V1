package com.fight_world.mono.domain.order_menu_history.service;

import static com.fight_world.mono.domain.payment.message.ExceptionMessage.ALREADY_PAY_IT;

import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order_menu_history.dto.request.OrderMenuHistoryCreateRequestDto;
import com.fight_world.mono.domain.order_menu_history.entity.OrderMenuHistory;
import com.fight_world.mono.domain.order_menu_history.exception.OrderMenuHistoryException;
import com.fight_world.mono.domain.order_menu_history.repository.OrderMenuHistoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderMenuHistoryServiceImplV1 implements OrderMenuHistoryService {

    private final OrderMenuHistoryRepository orderMenuHistoryRepository;

    @Override
    @Transactional
    public void createOrderMenuHistorys(
            List<OrderMenuHistoryCreateRequestDto> orderMenuHistoryCreateRequestDtoList, Order order) {

        if (orderMenuHistoryRepository.existsByOrderId(order.getId())) {
            throw new OrderMenuHistoryException(ALREADY_PAY_IT);
        }

        List<OrderMenuHistory> collect = orderMenuHistoryCreateRequestDtoList.stream()
                .map(dto -> OrderMenuHistory.of(dto, order)).toList();

        orderMenuHistoryRepository.saveAll(collect);
    }
}

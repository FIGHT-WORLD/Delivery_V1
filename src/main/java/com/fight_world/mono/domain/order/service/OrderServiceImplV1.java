package com.fight_world.mono.domain.order.service;

import static com.fight_world.mono.domain.order.message.ExceptionMessage.NOT_FOUND_ORDER;
import static com.fight_world.mono.domain.order.message.ExceptionMessage.ORDER_USER_VALID;

import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.dto.response.OrderDetailResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentDetailResponseDto;
import com.fight_world.mono.domain.order.exception.OrderException;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order.repository.OrderRepository;
import com.fight_world.mono.domain.order_menu.service.OrderMenuService;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImplV1 implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final StoreService storeService;
    private final OrderMenuService orderMenuService;

    @Override
    @Transactional
    public OrderResponseDto createOrder(
            UserDetailsImpl userDetails,
            OrderCreateRequestDto orderCreateRequestDto
    ) {

        User user = userService.findById(userDetails.getUserId());
        Store store = storeService.findById(orderCreateRequestDto.store_id());

        Order savedOrder = orderRepository.save(Order.of(orderCreateRequestDto, user, store));
        orderMenuService.createOrderMenus(orderCreateRequestDto.menu_ids(), savedOrder);

        return OrderResponseDto.from(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrders(
            UserDetailsImpl userDetails
    ) {

        return orderRepository.findAllByUserId(userDetails.getUserId())
                .stream().map(OrderResponseDto::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetailResponseDto getOrder(
            UserDetailsImpl userDetails,
            String orderId
    ) {

        OrderWithPaymentDetailResponseDto orderWithPaymentAndAddressDetail =
                orderRepository.findOrderWithPaymentAndAddressDetail(orderId).orElseThrow(
                        () -> new OrderException(NOT_FOUND_ORDER));

        return OrderDetailResponseDto.from(orderWithPaymentAndAddressDetail);
    }

    @Transactional
    public void deleteOrderFromUser(
            UserDetailsImpl userDetails, String orderId
    ) {

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderException(NOT_FOUND_ORDER)
        );

        if (!order.getUser().getId().equals(userDetails.getUserId())) {
            throw new OrderException(ORDER_USER_VALID);
        }

        order.delete(userDetails.getUserId());
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new OrderException(NOT_FOUND_ORDER)
        );
    }
}

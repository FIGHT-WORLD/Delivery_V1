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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<OrderResponseDto> getOrders(
            UserDetailsImpl userDetails,
            Pageable pageable,
            String store_name, String menu_name) {

        return orderRepository.findAllByUserIdCustom(userDetails.getUser().getId(), pageable, store_name, menu_name)
                .map(OrderResponseDto::from);
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
    @Transactional
    public void updateOrderToCooking(String orderId, UserDetailsImpl userDetails) {

        User user = userService.findById(userDetails.getUser().getId());

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderException(NOT_FOUND_ORDER)
        );

        if (order.getStatus() != CHECKING) {
            throw new OrderException(ORDER_CANT_CHANGE_STATUS);
        }

        // TODO 유섭님 머지 되면 이 부분 수정
        if (user.getRole() != MASTER && user.getRole() != MANAGER) {
            if (!order.getStore().getUser().getId().equals(user.getId())) {
                throw new OrderException(ORDER_USER_VALID);
            }
        }

        order.changeStatusTo(COOKING);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getStoreOrders(String storeId, UserDetailsImpl userDetails, Pageable pageable, Long userIdCond) {

        User user = userService.findById(userDetails.getUser().getId());

        Store store = storeService.findById(storeId);

        if (user.getRole() != MASTER && user.getRole() != MANAGER) {
            if (store.getUser().getId().equals(user.getId())) {
                throw new OrderException(ORDER_USER_VALID);
            }
        }

        Page<Order> orders = orderRepository.findAllByStoreIdWithOutCARTCustom(storeId, pageable, userIdCond);

        return orders.map(OrderResponseDto::from);
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new OrderException(NOT_FOUND_ORDER)
        );
    }
}

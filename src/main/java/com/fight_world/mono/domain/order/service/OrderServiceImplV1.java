package com.fight_world.mono.domain.order.service;

import static com.fight_world.mono.domain.order.message.ExceptionMessage.NOT_FOUND_ORDER;
import static com.fight_world.mono.domain.order.message.ExceptionMessage.ORDER_CANT_CHANGE_STATUS;
import static com.fight_world.mono.domain.order.message.ExceptionMessage.ORDER_USER_VALID;
import static com.fight_world.mono.domain.order.model.constant.OrderStatus.CHECKING;
import static com.fight_world.mono.domain.order.model.constant.OrderStatus.COOKING;

import com.fight_world.mono.domain.menu.model.Menu;
import com.fight_world.mono.domain.menu.service.MenuService;
import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.dto.request.OrderMenuCreateRequestDto;
import com.fight_world.mono.domain.order.dto.request.OrderMenuUpdateRequestDto;
import com.fight_world.mono.domain.order.dto.request.OrderUpdateRequestDto;
import com.fight_world.mono.domain.order.dto.response.OrderDetailResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderResponseDto;
import com.fight_world.mono.domain.order.exception.OrderException;
import com.fight_world.mono.domain.order.message.ExceptionMessage;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order.model.OrderMenu;
import com.fight_world.mono.domain.order.repository.OrderRepository;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.UserRole;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
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
    private final MenuService menuService;

    @Override
    @Transactional
    public OrderResponseDto createOrder(
            UserDetailsImpl userDetails,
            OrderCreateRequestDto requestDto
    ) {

        User user = userService.findById(userDetails.getUserId());
        Store store = storeService.findById(requestDto.store_id());

        Order order = Order.of(requestDto, user, store);

        for (OrderMenuCreateRequestDto orderMenuCreateRequestDto : requestDto.menu_ids()) {
            Menu menu = menuService.findById(orderMenuCreateRequestDto.menu_id());
            OrderMenu orderMenu = OrderMenu.of(orderMenuCreateRequestDto, menu);
            order.addOrderMenu(orderMenu);
        }

        Order savedOrder = orderRepository.save(order);

        return OrderResponseDto.from(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrder(
            UserDetailsImpl userDetails,
            String orderId,
            OrderUpdateRequestDto requestDto
    ) {

        Order order = findById(orderId);

        checkAuthority(userDetails, order);

        Store store = storeService.findById(requestDto.store_id());
        order.updateOrder(requestDto, store);

        for (OrderMenuUpdateRequestDto orderMenuRequestDto : requestDto.menu_ids()) {
            Menu menu = menuService.findById(orderMenuRequestDto.menu_id());
            OrderMenu orderMenu = OrderMenu.of(orderMenuRequestDto, menu);
            order.addOrderMenu(orderMenu);
        }

        return OrderResponseDto.from(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrders(
            UserDetailsImpl userDetails,
            Pageable pageable,
            String store_name, String menu_name
    ) {

        return orderRepository.findAllByUserIdCustom(userDetails.getUser().getId(), pageable, store_name, menu_name)
                              .map(OrderResponseDto::from);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetailResponseDto getOrder(
            UserDetailsImpl userDetails,
            String orderId
    ) {

        Order order = findById(orderId);

        checkAuthority(userDetails, order);

        return orderRepository.findOrderWithPaymentAndAddressDetail(orderId)
                              .orElseThrow(
                                      () -> new OrderException(NOT_FOUND_ORDER)
                              );
    }

    @Override
    @Transactional
    public void deleteOrder(
            UserDetailsImpl userDetails, String orderId
    ) {

        Order order = findById(orderId);

        checkAuthority(userDetails, order);

        order.delete(userDetails.getUserId());
    }

//    @Override
//    public BigDecimal getOrderTotalPrice(UserDetailsImpl userDetails, String orderId) {
//
//        Order order = findById(orderId);
//
//        if (userDetails.getUser().getRole() != MANAGER && userDetails.getUser().getRole() != MASTER) {
//            if (!order.getUser().getId().equals(userDetails.getUserId())) {
//                throw new OrderException(ORDER_USER_VALID);
//            }
//        }
//
//        return order.getTotalPrice();
//    }

    @Override
    @Transactional
    public void updateOrderToCooking(String orderId, UserDetailsImpl userDetails) {

        Order order = findById(orderId);

        if (!checkIsAdmin(userDetails)) {

            if(!order.getStore().getUser().getId().equals(userDetails.getUser().getId())) {
                throw new OrderException(ORDER_CANT_CHANGE_STATUS);
            }
        }

        if (order.getStatus() != CHECKING) {
            throw new OrderException(ORDER_CANT_CHANGE_STATUS);
        }

        order.changeStatusTo(COOKING);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getStoreOrders(String storeId, UserDetailsImpl userDetails, Pageable pageable, Long userIdCond) {

        Store store = storeService.findById(storeId);

        if (!checkIsAdmin(userDetails)); {
            if (!store.getUser().getId().equals(userDetails.getUserId())) {
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

    public void checkAuthority(UserDetailsImpl userDetails, Order order) {

        if (!checkIsAdmin(userDetails)) {
            checkIsOrderWriter(userDetails, order);
        }
    }

    public boolean checkIsAdmin(UserDetailsImpl userDetails) {

        User user = userService.findById(userDetails.getUserId());

        return user.getRole().equals(UserRole.MANAGER) || user.getRole().equals(UserRole.MASTER);
    }

    public void checkIsOrderWriter(UserDetailsImpl userDetails, Order order) {

        User user = userService.findById(userDetails.getUserId());

        if (!order.getUser().equals(user)) {
            throw new OrderException(ExceptionMessage.ORDER_UNAUTHORIZED);
        }
    }
}

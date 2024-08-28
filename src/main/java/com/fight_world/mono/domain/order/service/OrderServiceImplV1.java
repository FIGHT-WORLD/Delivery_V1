package com.fight_world.mono.domain.order.service;

import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.dto.response.OrderDetailResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderResponseDto;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order.repository.OrderRepository;
import com.fight_world.mono.domain.order_menu.service.OrderMenuService;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
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

        User user = userService.findById(userDetails.getUser().getId());
        Store store = storeService.findById(orderCreateRequestDto.store_id());

        Order savedOrder = orderRepository.save(Order.of(orderCreateRequestDto, user, store));
        orderMenuService.createOrderMenus(orderCreateRequestDto.menu_ids(), savedOrder);

        return OrderResponseDto.of(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getOrders(
            UserDetails userDetails
    ) {
        return List.of();
    }

    @Override
    public OrderDetailResponseDto getOrder(
            UserDetails userDetails,
            String orderId
    ) {
        return null;
    }

    @Override
    public void deleteOrder(
            UserDetails userDetails, String orderId
    ) {

    }
}

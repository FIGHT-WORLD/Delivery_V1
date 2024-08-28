package com.fight_world.mono.domain.order_menu.service;

import com.fight_world.mono.domain.menu.model.Menu;
import com.fight_world.mono.domain.menu.service.MenuService;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order_menu.dto.request.OrderMenuCreateRequestDto;
import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import com.fight_world.mono.domain.order_menu.repository.OrderMenuRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderMenuServiceImplV1 implements OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;
    private final MenuService menuService;

    @Override
    @Transactional
    public void createOrderMenus(List<OrderMenuCreateRequestDto> orderMenuCreateRequestDtoList, Order order) {

        List<OrderMenu> orderMenus = new ArrayList<>();

        for (OrderMenuCreateRequestDto orderMenuCreateRequestDto : orderMenuCreateRequestDtoList) {
            Menu menu = menuService.findById(orderMenuCreateRequestDto.menu_id());
            OrderMenu orderMenu = OrderMenu.of(orderMenuCreateRequestDto, order, menu);
            order.addOrderMenu(orderMenu);
            orderMenus.add(orderMenu);
        }

        orderMenuRepository.saveAll(orderMenus);
    }
}

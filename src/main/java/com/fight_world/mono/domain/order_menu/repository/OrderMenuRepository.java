package com.fight_world.mono.domain.order_menu.repository;

import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, String> {

}

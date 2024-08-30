package com.fight_world.mono.domain.order_menu_history.repository;

import com.fight_world.mono.domain.order_menu_history.entity.OrderMenuHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuHistoryRepository extends JpaRepository<OrderMenuHistory, String> {

    boolean existsByOrderId(String orderId);
}

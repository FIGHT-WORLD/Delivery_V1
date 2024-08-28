package com.fight_world.mono.domain.order.repository;

import com.fight_world.mono.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}

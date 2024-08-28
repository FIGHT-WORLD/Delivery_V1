package com.fight_world.mono.domain.order.repository;

import com.fight_world.mono.domain.order.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByUserId(Long id);
}

package com.fight_world.mono.domain.order.repository;

import com.fight_world.mono.domain.order.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, String>, OrderQueryRepository {

    List<Order> findAllByUserId(Long id);

    @Query("SELECT o "
            + "FROM Order o "
            + "JOIN o.store s "
            + "WHERE s.id = :storeId AND o.status != 'CART'")
    List<Order> findAllByStoreIdWithOutCART(String storeId);
}

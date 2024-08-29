package com.fight_world.mono.domain.payment.repository;

import com.fight_world.mono.domain.payment.model.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query("SELECT p "
            + "FROM Payment p "
            + "JOIN FETCH Order o ON o.id = p.order.id "
            + "JOIN FETCH User u ON o.user.id = u.id "
            + "WHERE o.user.id = :userId"
    )
    List<Payment> findAllByUserId(@Param("userId") Long userId);
}

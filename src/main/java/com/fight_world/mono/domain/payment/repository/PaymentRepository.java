package com.fight_world.mono.domain.payment.repository;

import com.fight_world.mono.domain.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}

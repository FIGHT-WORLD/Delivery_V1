package com.fight_world.mono.domain.payment.service;

import com.fight_world.mono.domain.payment.model.Payment;
import java.util.Optional;

public interface PaymentService {

    Optional<Payment> getOptionalPaymentByOrderId(String orderId);
}

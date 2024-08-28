package com.fight_world.mono.domain.payment.service;

import com.fight_world.mono.domain.payment.model.Payment;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImplV1 implements PaymentService {

    @Override
    public Optional<Payment> getOptionalPaymentByOrderId(String orderId) {
        return Optional.empty();
    }
}

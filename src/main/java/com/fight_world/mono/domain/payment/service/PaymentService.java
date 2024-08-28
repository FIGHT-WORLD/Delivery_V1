package com.fight_world.mono.domain.payment.service;

import com.fight_world.mono.domain.payment.dto.request.PaymentCreateRequestDto;
import com.fight_world.mono.domain.payment.dto.response.PaymentResponseDto;
import com.fight_world.mono.domain.payment.model.Payment;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.Optional;

public interface PaymentService {

    Optional<Payment> getOptionalPaymentByOrderId(String orderId);

    PaymentResponseDto createPayment(UserDetailsImpl userDetails, PaymentCreateRequestDto requestDto);
}

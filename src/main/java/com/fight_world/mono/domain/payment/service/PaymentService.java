package com.fight_world.mono.domain.payment.service;

import com.fight_world.mono.domain.payment.dto.request.PaymentCreateRequestDto;
import com.fight_world.mono.domain.payment.dto.response.PaymentResponseDto;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;

public interface PaymentService {

    PaymentResponseDto createPayment(UserDetailsImpl userDetails, PaymentCreateRequestDto requestDto);

    PaymentResponseDto getPayment(UserDetailsImpl userDetails, String paymentId);

    List<PaymentResponseDto> getPayments(UserDetailsImpl userDetails);
}

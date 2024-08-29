package com.fight_world.mono.domain.payment.service;

import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order.model.constant.OrderStatus;
import com.fight_world.mono.domain.order.service.OrderService;
import com.fight_world.mono.domain.payment.dto.request.PaymentCreateRequestDto;
import com.fight_world.mono.domain.payment.dto.response.PaymentResponseDto;
import com.fight_world.mono.domain.payment.exception.PaymentException;
import com.fight_world.mono.domain.payment.message.ExceptionMessage;
import com.fight_world.mono.domain.payment.model.Payment;
import com.fight_world.mono.domain.payment.repository.PaymentRepository;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImplV1 implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public PaymentResponseDto createPayment(
            UserDetailsImpl userDetails,
            PaymentCreateRequestDto requestDto
    ) {

        Order order = orderService.findById(requestDto.order_id());

        Payment savedPayment = paymentRepository.save(Payment.of(order, requestDto));

        order.changeStatusTo(OrderStatus.CHECKING);

        return PaymentResponseDto.from(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDto getPayment(UserDetailsImpl userDetails, String paymentId) {

        Payment payment = paymentRepository.findById(paymentId).orElseThrow(
                () -> new PaymentException(ExceptionMessage.NOT_FOUND_PAYMENT)
        );

        if (!payment.getOrder().getUser().getId().equals(userDetails.getUser().getId())) {
            throw new PaymentException(ExceptionMessage.GUARD);
        }

        return PaymentResponseDto.from(payment);
    }
}

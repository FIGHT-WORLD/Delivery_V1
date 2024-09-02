package com.fight_world.mono.domain.payment.service;

import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order.model.constant.OrderStatus;
import com.fight_world.mono.domain.order.service.OrderService;
import com.fight_world.mono.domain.order_menu_history.dto.request.OrderMenuHistoryCreateRequestDto;
import com.fight_world.mono.domain.order_menu_history.service.OrderMenuHistoryService;
import com.fight_world.mono.domain.payment.dto.request.PaymentCreateRequestDto;
import com.fight_world.mono.domain.payment.dto.response.PaymentResponseDto;
import com.fight_world.mono.domain.payment.exception.PaymentException;
import com.fight_world.mono.domain.payment.message.ExceptionMessage;
import com.fight_world.mono.domain.payment.model.Payment;
import com.fight_world.mono.domain.payment.repository.PaymentRepository;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImplV1 implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;
    private final OrderMenuHistoryService orderMenuHistoryService;

    @Override
    @Transactional
    public PaymentResponseDto createPayment(
            UserDetailsImpl userDetails,
            PaymentCreateRequestDto requestDto
    ) {

        Order order = orderService.findById(requestDto.order_id());

        if (paymentRepository.existsByOrderId(order.getId())) {
            throw new PaymentException(ExceptionMessage.ALREADY_PAY_IT);
        }

        if (!order.getTotalPrice().equals(requestDto.total_price())) {
            throw new PaymentException(ExceptionMessage.NOT_MATCH_TOTAL_PRICE);
        }

        Payment savedPayment = paymentRepository.save(Payment.of(order, requestDto));

        order.changeStatusTo(OrderStatus.CHECKING);

        List<OrderMenuHistoryCreateRequestDto> orderMenuHistoryCreateRequestDtoList = order.getOrderMenus()
                .stream()
                .map(OrderMenuHistoryCreateRequestDto::from)
                .toList();
        orderMenuHistoryService.createOrderMenuHistorys(orderMenuHistoryCreateRequestDtoList, order);

        return PaymentResponseDto.from(savedPayment);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDto getPayment(UserDetailsImpl userDetails, String paymentId) {

        Payment payment = paymentRepository.findById(paymentId).orElseThrow(
                () -> new PaymentException(ExceptionMessage.NOT_FOUND_PAYMENT)
        );

        if (!payment.getOrder().getUser().getId().equals(userDetails.getUserId())) {
            throw new PaymentException(ExceptionMessage.GUARD);
        }

        return PaymentResponseDto.from(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDto> getPayments(UserDetailsImpl userDetails) {

        List<Payment> payments = paymentRepository.findAllByUserId(userDetails.getUserId());

        return payments.stream().map(PaymentResponseDto::from).collect(Collectors.toList());
    }
}

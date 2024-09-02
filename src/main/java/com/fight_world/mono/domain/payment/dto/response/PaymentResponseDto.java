package com.fight_world.mono.domain.payment.dto.response;

import com.fight_world.mono.domain.payment.model.Payment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PaymentResponseDto(
        String payment_id,
        String order_id,
        BigDecimal total_price,
        String payment_type,
        LocalDateTime created_at
) {

    public static PaymentResponseDto from(Payment payment) {

        return PaymentResponseDto.builder()
                                 .payment_id(payment.getId())
                                 .order_id(payment.getOrder().getId())
                                 .total_price(payment.getTotalPrice().getValue())
                                 .payment_type(payment.getPaymentType().getType())
                                 .created_at(payment.getCreatedAt())
                                 .build();
    }
}

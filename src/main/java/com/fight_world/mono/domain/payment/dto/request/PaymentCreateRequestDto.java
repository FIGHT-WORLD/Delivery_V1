package com.fight_world.mono.domain.payment.dto.request;

public record PaymentCreateRequestDto(
        String order_id,
        String payment_type,
        String pg_payment_id
) {

}

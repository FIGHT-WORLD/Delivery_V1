package com.fight_world.mono.domain.order.dto.response;

import com.fight_world.mono.domain.order.model.constant.OrderDeliveryType;
import com.fight_world.mono.domain.order.model.constant.OrderStatus;
import com.fight_world.mono.domain.payment.model.constant.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderWithPaymentDetailBeforeMixResponseDto(
        String order_id,
        String store_id,
        String store_name,
        OrderDeliveryType delivery_type,
        LocalDateTime created_at,
        OrderStatus order_status,
        String address,
        String detail_address,
        String request,
        BigDecimal total_price,
        PaymentType payment_type
) {

}

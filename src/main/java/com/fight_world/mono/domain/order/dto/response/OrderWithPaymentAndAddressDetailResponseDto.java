package com.fight_world.mono.domain.order.dto.response;

import com.fight_world.mono.domain.order.model.constant.OrderDeliveryType;
import com.fight_world.mono.domain.order.model.constant.OrderStatus;
import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import com.fight_world.mono.domain.payment.model.constant.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record OrderWithPaymentAndAddressDetailResponseDto(
        String order_id,
        String store_id,
        String store_name,
        Set<OrderMenu> menu_ids,
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

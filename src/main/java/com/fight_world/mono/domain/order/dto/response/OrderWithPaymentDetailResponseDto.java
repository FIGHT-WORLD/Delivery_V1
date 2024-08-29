package com.fight_world.mono.domain.order.dto.response;

import com.fight_world.mono.domain.order.model.constant.OrderDeliveryType;
import com.fight_world.mono.domain.order.model.constant.OrderStatus;
import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import com.fight_world.mono.domain.payment.model.constant.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
public record OrderWithPaymentDetailResponseDto(
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

    public static OrderWithPaymentDetailResponseDto of(OrderWithPaymentDetailBeforeMixResponseDto beforeMixResponseDto, Set<OrderMenu> menuIds) {

        return OrderWithPaymentDetailResponseDto.builder()
                .order_id(beforeMixResponseDto.order_id())
                .store_id(beforeMixResponseDto.store_id())
                .store_name(beforeMixResponseDto.store_name())
                .menu_ids(menuIds)
                .delivery_type(beforeMixResponseDto.delivery_type())
                .created_at(beforeMixResponseDto.created_at())
                .order_status(beforeMixResponseDto.order_status())
                .address(beforeMixResponseDto.address())
                .detail_address(beforeMixResponseDto.detail_address())
                .request(beforeMixResponseDto.request())
                .total_price(beforeMixResponseDto.total_price() == null ? null : beforeMixResponseDto.total_price())
                .payment_type(beforeMixResponseDto.payment_type() == null ? null : beforeMixResponseDto.payment_type())
                .build();
    }
}

package com.fight_world.mono.domain.order.dto.response;

import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order_menu.dto.reponse.OrderMenuResponseDto;
import com.fight_world.mono.domain.payment.model.Payment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record OrderDetailResponseDto(
        String order_id,
        String store_id,
        String store_name,
        Set<OrderMenuResponseDto> menu_ids,
        String delivery_type,
        LocalDateTime created_at,
        String order_status,
        String address,
        String detail_address,
        String request,
        BigDecimal total_price,
        String payment_type
) {

    public static OrderDetailResponseDto of(Order order, Optional<Payment> payment) {

        return OrderDetailResponseDto.builder()
                .order_id(order.getId())
                .store_id(order.getStore().getId())
                .store_name(order.getStore().getName())
                .menu_ids(order.getOrderMenus().stream().map(OrderMenuResponseDto::from).collect(
                        Collectors.toSet()))
                .delivery_type(order.getDeliveryType().getType())
                .created_at(order.getCreatedAt())
                .order_status(order.getStatus().getStatus())
                .address(order.getUserAddress().getAddress())
                .detail_address(order.getUserAddress().getDetailAddress())
                .request(order.getUserAddress().getRequest())
                .total_price(payment.map(value -> value.getTotalPrice().getValue()).orElse(null))
                .payment_type(payment.map(value -> value.getPaymentType().getType()).orElse(null))
                .build();
    }
}

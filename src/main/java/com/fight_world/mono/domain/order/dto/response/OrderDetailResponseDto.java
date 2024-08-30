package com.fight_world.mono.domain.order.dto.response;

import com.fight_world.mono.domain.order_menu.dto.reponse.OrderMenuResponseDto;
import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import com.fight_world.mono.domain.order_menu_history.entity.OrderMenuHistory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public static OrderDetailResponseDto ofWithOrderMenu(OrderWithPaymentDetailBeforeMixResponseDto responseDto, Set<OrderMenu> orderMenuSet) {

        return OrderDetailResponseDto.builder()
                                     .order_id(responseDto.order_id())
                                     .store_id(responseDto.store_id())
                                     .store_name(responseDto.store_name())
                                     .menu_ids(orderMenuSet.stream()
                                                           .map(OrderMenuResponseDto::from)
                                                           .collect(Collectors.toSet()))
                                     .delivery_type(responseDto.delivery_type().getType())
                                     .created_at(responseDto.created_at())
                                     .order_status(responseDto.order_status().getStatus())
                                     .address(responseDto.address())
                                     .detail_address(responseDto.detail_address())
                                     .request(responseDto.request())
                                     .total_price(orderMenuSet.stream()
                                             .map(OrderMenu::getTotalPrice)
                                             .reduce(BigDecimal.ZERO, BigDecimal::add))
                                     .payment_type(responseDto.payment_type() == null ? null : responseDto.payment_type().getType())
                                     .build();
    }

    public static OrderDetailResponseDto ofWithOrderMenuHistory(OrderWithPaymentDetailBeforeMixResponseDto responseDto, Set<OrderMenuHistory> orderMenuHistorySet) {

        return OrderDetailResponseDto.builder()
                .order_id(responseDto.order_id())
                .store_id(responseDto.store_id())
                .store_name(responseDto.store_name())
                .menu_ids(orderMenuHistorySet.stream()
                        .map(OrderMenuResponseDto::from)
                        .collect(Collectors.toSet()))
                .delivery_type(responseDto.delivery_type().getType())
                .created_at(responseDto.created_at())
                .order_status(responseDto.order_status().getStatus())
                .address(responseDto.address())
                .detail_address(responseDto.detail_address())
                .request(responseDto.request())
                .total_price(responseDto.total_price() == null ? null : responseDto.total_price())
                .payment_type(responseDto.payment_type() == null ? null : responseDto.payment_type().getType())
                .build();
    }
}

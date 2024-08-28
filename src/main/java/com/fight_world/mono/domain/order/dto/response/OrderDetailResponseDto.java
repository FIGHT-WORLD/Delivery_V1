package com.fight_world.mono.domain.order.dto.response;

import com.fight_world.mono.domain.order_menu.dto.reponse.OrderMenuResponseDto;
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

    public static OrderDetailResponseDto of(OrderWithPaymentDetailResponseDto responseDto) {

        return OrderDetailResponseDto.builder()
                                     .order_id(responseDto.order_id())
                                     .store_id(responseDto.store_id())
                                     .store_name(responseDto.store_name())
                                     .menu_ids(responseDto.menu_ids()
                                                          .stream()
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

package com.fight_world.mono.domain.order.repository;

import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentDetailBeforeMixResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentDetailResponseDto;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface OrderQueryRepository {

    Optional<OrderWithPaymentDetailResponseDto> findOrderWithPaymentAndAddressDetail(@Param("orderId") String orderId);
}

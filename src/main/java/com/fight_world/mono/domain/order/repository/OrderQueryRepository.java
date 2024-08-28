package com.fight_world.mono.domain.order.repository;

import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentAndAddressDetailResponseDto;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface OrderQueryRepository {

    Optional<OrderWithPaymentAndAddressDetailResponseDto> findOrderWithPaymentAndAddressDetail(@Param("orderId") String orderId);
}

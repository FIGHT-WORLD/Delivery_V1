package com.fight_world.mono.domain.order.repository;

import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentDetailResponseDto;
import com.fight_world.mono.domain.order.model.Order;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface OrderQueryRepository {

    Optional<OrderWithPaymentDetailResponseDto> findOrderWithPaymentAndAddressDetail(@Param("orderId") String orderId);

    Page<Order> findAllByUserIdCustom(Long userId, Pageable pageable, String store_name,
            String menu_name);

    Page<Order> findAllByStoreIdWithOutCARTCustom(String storeId, Pageable pageable, Long userCond);
}

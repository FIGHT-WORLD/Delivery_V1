package com.fight_world.mono.domain.order.repository;

import static com.fight_world.mono.domain.order.model.QOrder.order;
import static com.fight_world.mono.domain.payment.model.QPayment.payment;
import static com.fight_world.mono.domain.store.model.QStore.store;
import static com.fight_world.mono.domain.user_address.model.QUserAddress.userAddress;

import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentAndAddressDetailResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.Optional;

public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public OrderQueryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<OrderWithPaymentAndAddressDetailResponseDto> findOrderWithPaymentAndAddressDetail(String orderId) {

        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(OrderWithPaymentAndAddressDetailResponseDto.class,
                        order.id,
                        store.id,
                        store.name,
                        order.orderMenus,
                        order.deliveryType,
                        order.createdAt,
                        order.status,
                        userAddress.address,
                        userAddress.detailAddress,
                        userAddress.request,
                        payment.totalPrice.value,
                        payment.paymentType
                ))
                .from(order)
                .join(order.store, store)
                .join(order.userAddress, userAddress)
                .join(payment).on(payment.order.id.eq(order.id))
                .where(order.id.eq(orderId))
                .fetchOne());
    }
}
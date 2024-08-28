package com.fight_world.mono.domain.order.repository;

import static com.fight_world.mono.domain.order.model.QOrder.order;
import static com.fight_world.mono.domain.order_menu.model.QOrderMenu.*;
import static com.fight_world.mono.domain.payment.model.QPayment.payment;
import static com.fight_world.mono.domain.store.model.QStore.store;

import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentDetailBeforeMixResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentDetailResponseDto;
import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public OrderQueryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<OrderWithPaymentDetailResponseDto> findOrderWithPaymentAndAddressDetail(String orderId) {

        Optional<OrderWithPaymentDetailBeforeMixResponseDto> beforeMixResponseDto = Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(
                                OrderWithPaymentDetailBeforeMixResponseDto.class,
                                order.id.as("order_id"),
                                store.id.as("store_id"),
                                store.name.as("store_name"),
                                order.deliveryType.as("delivery_type"),
                                order.createdAt.as("created_at"),
                                order.status.as("order_status"),
                                order.address.as("address"),
                                order.detailAddress.as("detail_address"),
                                order.request.as("request"),
                                payment.totalPrice.value.as("total_price"),
                                payment.paymentType.as("payment_type")
                        ))
                        .from(order)
                        .join(order.store, store)
                        .leftJoin(orderMenu).on(orderMenu.order.id.eq(order.id))
                        .leftJoin(payment).on(payment.order.id.eq(order.id))
                        .where(order.id.eq(orderId))
                        .fetchOne());

        if (!beforeMixResponseDto.isPresent()) {
            return Optional.empty();
        }

        Set<OrderMenu> orderMenus = getOrderMenus(orderId);

        return Optional.of(OrderWithPaymentDetailResponseDto.of(beforeMixResponseDto.get(), orderMenus));
    }

    private Set<OrderMenu> getOrderMenus(String orderId) {
        return queryFactory
                .selectFrom(orderMenu)
                .where(orderMenu.order.id.eq(orderId))
                .fetch()
                .stream()
                .collect(Collectors.toSet());
    }
}
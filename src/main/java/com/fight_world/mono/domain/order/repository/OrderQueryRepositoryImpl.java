package com.fight_world.mono.domain.order.repository;

import static com.fight_world.mono.domain.menu.model.QMenu.menu;
import static com.fight_world.mono.domain.order.model.QOrder.order;
import static com.fight_world.mono.domain.order_menu.model.QOrderMenu.orderMenu;
import static com.fight_world.mono.domain.payment.model.QPayment.payment;
import static com.fight_world.mono.domain.store.model.QStore.store;
import static com.fight_world.mono.domain.user.model.QUser.user;

import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentDetailBeforeMixResponseDto;
import com.fight_world.mono.domain.order.dto.response.OrderWithPaymentDetailResponseDto;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

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

    @Override
    public Page<Order> findAllByUserIdCustom(Long userId, Pageable pageable, String store_name,
            String menu_name) {

        List<Order> content = queryFactory.selectFrom(order)
                .join(order.store, store)
                .join(order.user, user)
                .join(order.orderMenus, orderMenu)
                .join(orderMenu.menu, menu)
                .where(order.user.id.eq(userId), storeNameContains(store_name), menuNameContains(menu_name))
                .orderBy(getOrderSpecifiers(pageable.getSort()).toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> total = queryFactory.select(order.count())
                .from(order)
                .join(order.user, user)
                .where(order.user.id.eq(userId), storeNameContains(store_name), menuNameContains(menu_name));

        return PageableExecutionUtils.getPage(content, pageable, total::fetchOne);
    }

    private BooleanExpression storeNameContains(String storeName) {
        return storeName != null ?
                Expressions.stringTemplate("CAST(function('replace', {0}, ' ', '') AS string)", store.name)
                        .contains(storeName)
                : null;
    }

    private BooleanExpression menuNameContains(String menuName) {
        return menuName != null ?
                Expressions.stringTemplate("CAST(function('replace', {0}, ' ', '') AS string)", menu.name)
                        .contains(menuName)
                : null;
    }

    private List<OrderSpecifier> getOrderSpecifiers(Sort sort) {

        List<OrderSpecifier> orders = new ArrayList<>();

        sort.stream().forEach(o -> {
            com.querydsl.core.types.Order direction = o.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC;
            String property = o.getProperty();
            PathBuilder<Order> orderByExpression = new PathBuilder<>(Order.class, "order");
            orders.add(new OrderSpecifier(direction, orderByExpression.get(property)));
        });

        return orders;
    }
}
package com.fight_world.mono.domain.order.model;

import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.model.constant.OrderDeliveryType;
import com.fight_world.mono.domain.order.model.constant.OrderStatus;
import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_order")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderDeliveryType deliveryType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "order")
    private Set<OrderMenu> orderMenus = new HashSet<>();

    @Builder(access = AccessLevel.PRIVATE)
    public Order(OrderStatus orderStatus, OrderDeliveryType orderDeliveryType, User user, Store store) {
        this.status = orderStatus;
        this.deliveryType = orderDeliveryType;
        this.user = user;
        this.store = store;
    }

    public static Order of(OrderCreateRequestDto requestDto, User user, Store store) {

        return Order.builder()
                    .orderDeliveryType(OrderDeliveryType.valueOf(requestDto.delivery_type()))
                    .orderStatus(OrderStatus.CART)
                    .user(user)
                    .store(store)
                    .build();
    }

    public void addOrderMenu(OrderMenu orderMenu) {
        this.orderMenus.add(orderMenu);
    }
}

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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private String request;

    @OneToMany(mappedBy = "order")
    private Set<OrderMenu> orderMenus = new HashSet<>();

    @Builder(access = AccessLevel.PRIVATE)
    public Order(OrderStatus status, OrderDeliveryType deliveryType, User user, Store store,
            String address, String detailAddress, String request, Set<OrderMenu> orderMenus) {
        this.status = status;
        this.deliveryType = deliveryType;
        this.user = user;
        this.store = store;
        this.address = address;
        this.detailAddress = detailAddress;
        this.request = request;
    }


    public static Order of(OrderCreateRequestDto requestDto, User user, Store store) {

        return Order.builder()
                    .deliveryType(OrderDeliveryType.valueOf(requestDto.delivery_type()))
                    .status(OrderStatus.CART)
                    .user(user)
                    .store(store)
                    .address(requestDto.address())
                    .detailAddress(requestDto.detail_address())
                    .request(requestDto.request())
                    .build();
    }

    public void addOrderMenu(OrderMenu orderMenu) {

        this.orderMenus.add(orderMenu);
    }

    public void delete(Long userId) {

        super.setDeleted(userId);
    }

    public void changeStatusTo(OrderStatus orderStatus) {

        this.status = orderStatus;
    }

    public BigDecimal getTotalPrice() {

        return this.orderMenus.stream()
                .map(orderMenu -> orderMenu.getMenu().getMenuPrice().getValue().multiply(new BigDecimal(orderMenu.getCnt().getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

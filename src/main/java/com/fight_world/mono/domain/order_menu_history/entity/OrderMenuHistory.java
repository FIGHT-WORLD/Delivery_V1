package com.fight_world.mono.domain.order_menu_history.entity;

import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order_menu_history.dto.request.OrderMenuHistoryCreateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_order_menu_history")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenuHistory extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, updatable = false)
    private String menuId; // 이거 어떻게 할지 고민중이에요..ㅠㅠ

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private BigDecimal price;

    @Column(nullable = false, updatable = false)
    private String description;

    @Column(nullable = false, updatable = false)
    private Integer cnt;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private Order order;

    @Builder(access = AccessLevel.PRIVATE)
    public OrderMenuHistory(String menuId, String name, BigDecimal price, String description,
            Integer cnt, Order order) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.cnt = cnt;
        this.order = order;
    }

    public static OrderMenuHistory of(OrderMenuHistoryCreateRequestDto orderMenuHistoryCreateRequestDto, Order order) {

        return OrderMenuHistory.builder()
                .name(orderMenuHistoryCreateRequestDto.name())
                .description(orderMenuHistoryCreateRequestDto.description())
                .cnt(orderMenuHistoryCreateRequestDto.cnt())
                .price(orderMenuHistoryCreateRequestDto.price())
                .order(order)
                .menuId(orderMenuHistoryCreateRequestDto.menuId())
                .build();
    }
}

package com.fight_world.mono.domain.order.model;


import com.fight_world.mono.domain.menu.model.Menu;
import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.order.dto.request.OrderMenuCreateRequestDto;
import com.fight_world.mono.domain.order.dto.request.OrderMenuUpdateRequestDto;
import com.fight_world.mono.domain.order.model.value_object.OrderMenuCnt;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "p_order_menu")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenu extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Embedded
    private OrderMenuCnt cnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Builder(access = AccessLevel.PRIVATE)
    public OrderMenu(OrderMenuCnt cnt, Menu menu) {
        this.cnt = cnt;
        this.menu = menu;
    }

    public static OrderMenu of(OrderMenuCreateRequestDto requestDto, Menu menu) {

        return OrderMenu.builder()
                        .cnt(new OrderMenuCnt(requestDto.cnt()))
                        .menu(menu)
                        .build();
    }

    public static OrderMenu of(OrderMenuUpdateRequestDto requestDto, Menu menu) {

        return OrderMenu.builder()
                .cnt(new OrderMenuCnt(requestDto.cnt()))
                .menu(menu)
                .build();
    }

    public BigDecimal getTotalPrice() {

        return this.menu.getMenuPrice().getValue().multiply(BigDecimal.valueOf(this.cnt.getValue()));
    }

    public void setOrder(Order order) {

        this.order = order;
    }
}

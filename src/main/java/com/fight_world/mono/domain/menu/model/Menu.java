package com.fight_world.mono.domain.menu.model;

import com.fight_world.mono.domain.menu.model.value_object.MenuDescription;
import com.fight_world.mono.domain.menu.model.value_object.MenuPrice;
import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.store.model.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_menu")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private String name;

    @Embedded
    private MenuPrice menuPrice;

    @Embedded
    private MenuDescription menuDescription;

    @Column(name="on_sale", nullable = false) //enum으로 변경하기
    private String onSale;

}

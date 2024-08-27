package com.fight_world.mono.domain.store.model;

import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.store.dto.request.StoreRegisterRequestDto;
import com.fight_world.mono.domain.store.model.constant.StoreStatus;
import com.fight_world.mono.domain.store.model.value_object.StorePhoneNumber;
import com.fight_world.mono.domain.store_category.model.StoreCategory;
import com.fight_world.mono.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "p_store")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private StoreCategory storeCategory;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "open_at")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openAt;

    @Column(name = "close_at")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Embedded
    private StorePhoneNumber phoneNumber;

    @Builder(access = AccessLevel.PRIVATE)
    public Store(String name, String address, LocalTime openAt, LocalTime closeAt, StoreStatus status, StorePhoneNumber phoneNumber, StoreCategory storeCategory, User user) {
        this.name = name;
        this.address = address;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.storeCategory = storeCategory;
        this.user = user;
    }

    public static Store of(StoreRegisterRequestDto requestDto, StoreCategory storeCategory, User user) {

        return Store.builder()
                .name(requestDto.name())
                .address(requestDto.address())
                .openAt(requestDto.openAt())
                .closeAt(requestDto.closeAt())
                .phoneNumber(new StorePhoneNumber(requestDto.phoneNumber()))
                .status(StoreStatus.CLOSED)
                .storeCategory(storeCategory)
                .user(user)
                .build();
    }

    public void changeStatus(StoreStatus storeStatus) {
        this.status = storeStatus;
    }
}

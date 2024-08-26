package com.fight_world.mono.domain.user_address.model;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user_address.dto.request.CreateUserAddressRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAddress {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column
    String address;

    @Column
    String detailAddress;

    @Column
    String request;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    User user;

//    @Column
//    @OneToOne
//    GovernmantDistrict governmantDistrict;

    @Builder(access = AccessLevel.PRIVATE)
    public UserAddress(String address, String detailAddress, String request, User user) {
        this.address = address;
        this.detailAddress = detailAddress;
        this.request = request;
        this.user = user;
    }

    public static UserAddress of(CreateUserAddressRequestDto requestDto) {

        return UserAddress.builder()
                .address(requestDto.address())
                .detailAddress(requestDto.detailAddress())
                .request(requestDto.request())
                .user(requestDto.user())
                .build();
    }
}

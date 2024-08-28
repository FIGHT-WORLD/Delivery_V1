package com.fight_world.mono.domain.user_address.model;

import com.fight_world.mono.domain.model.TimeBase;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_user_address")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAddress extends TimeBase {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String detailAddress;

    @Column
    private String request;

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

    /*
    이 메서드를 쓸때면 로그인 된 상태 이므로 reqeustDto에서 user를 받지 않고 securitycontext에서 받겠습니다.
     */
    public static UserAddress of(CreateUserAddressRequestDto requestDto, User user) {

        return UserAddress.builder()
                .address(requestDto.address())
                .detailAddress(requestDto.detailAddress())
                .request(requestDto.request())
                .user(user)
                .build();
    }
}

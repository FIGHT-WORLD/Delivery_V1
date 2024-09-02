package com.fight_world.mono.domain.user_address.service;

import com.fight_world.mono.domain.user_address.dto.request.CreateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.request.UpdateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.response.CreateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.DeleteUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressListResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.UpdateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.model.UserAddress;
import com.fight_world.mono.global.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAddressService {

    // 주소 생성
    CreateUserAddressResponseDto createUserAddress(CreateUserAddressRequestDto requestDto,
            UserDetailsImpl userDetails);

    // 주소 상세 조회
    GetUserAddressResponseDto getUserAddress(String userAddressId, UserDetailsImpl userDetails);

    // 주소 수정
    UpdateUserAddressResponseDto updateUserAddress(String userAddressId,
            UpdateUserAddressRequestDto requestDto, UserDetailsImpl userDetails);

    Page<GetUserAddressListResponseDto> getUserAddressList(UserDetailsImpl userDetails,
            Pageable pageable);

    DeleteUserAddressResponseDto deleteUserAddress(String userAddressId,
            UserDetailsImpl userDetails);

    UserAddress findUserAddressById(String userAddressId);

}

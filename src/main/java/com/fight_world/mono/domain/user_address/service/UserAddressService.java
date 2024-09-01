package com.fight_world.mono.domain.user_address.service;

import com.fight_world.mono.domain.user_address.dto.request.CreateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.request.UpdateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.response.CreateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.DeleteUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressListResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.UpdateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.model.UserAddress;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;

public interface UserAddressService {

    CreateUserAddressResponseDto createUserAddress(CreateUserAddressRequestDto requestDto,
            UserDetailsImpl userDetails);

    UpdateUserAddressResponseDto updateUserAddress(UpdateUserAddressRequestDto requestDto);

    List<GetUserAddressListResponseDto> getUserAddressList(UserDetailsImpl userDetails);

    DeleteUserAddressResponseDto deleteUserAddress(String userAddressId,
            UserDetailsImpl userDetails);

    boolean isOwner(UserDetailsImpl userDetails);

    boolean isManager(UserDetailsImpl userDetails);

    boolean isCustomer(UserDetailsImpl userDetails);

    boolean isMaster(UserDetailsImpl userDetails);

    UserAddress findUserAddressById(String userAddressId);

}

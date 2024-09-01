package com.fight_world.mono.domain.user_address.service;

import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.FORBIDDEN_USER_AUTHORITY;
import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.INVALID_USER_AUTHORIZATION;
import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.NOT_FOUND_DELETED_USER;
import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.NOT_FOUND_USER_ADDRESS;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.UserRole;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.domain.user_address.dto.request.CreateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.request.UpdateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.response.CreateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.DeleteUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressListResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.UpdateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.exception.UserAddressException;
import com.fight_world.mono.domain.user_address.model.UserAddress;
import com.fight_world.mono.domain.user_address.repository.UserAddressRepository;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository userAddressRepository;
    private final UserService userService;

    @Override
    public CreateUserAddressResponseDto createUserAddress(
            CreateUserAddressRequestDto requestDto,
            UserDetailsImpl userDetails) {

        // 삭제된 회원인지
        if (!userDetails.isAccountNonExpired()) {
            throw new UserAddressException(NOT_FOUND_DELETED_USER);
        }
        User user = userDetails.getUser();
        UserAddress savedUserAddress = UserAddress.of(requestDto, user);
        userAddressRepository.save(savedUserAddress);

        return CreateUserAddressResponseDto.from(savedUserAddress);
    }

    @Override
    public UpdateUserAddressResponseDto updateUserAddress(UpdateUserAddressRequestDto requestDto) {
        return null;
    }

    @Override
    public List<GetUserAddressListResponseDto> getUserAddressList(UserDetailsImpl userDetails) {

        if (isMaster(userDetails) || isManager(userDetails)) {

            return userAddressRepository
                    .findAll(Sort.by(Sort.DEFAULT_DIRECTION))
                    .stream()
                    .map(GetUserAddressListResponseDto::of)
                    .toList();
        } else {
            throw new UserAddressException(FORBIDDEN_USER_AUTHORITY);
        }
    }

    @Override
    public DeleteUserAddressResponseDto deleteUserAddress(
            String userAddressId,
            UserDetailsImpl userDetails) {

        if (!userDetails.isAccountNonExpired()) {
            throw new UserAddressException(NOT_FOUND_DELETED_USER);
        }
        if (isOwner(userDetails) || isCustomer(userDetails)) {
            UserAddress deletedAddress = userAddressRepository.findById(userAddressId)
                    .orElseThrow(() -> new UserAddressException(NOT_FOUND_USER_ADDRESS));

            if (deletedAddress.getUser() != userDetails.getUser()) {
                throw new UserAddressException(INVALID_USER_AUTHORIZATION);
            }
            ;
        }

        return null;
    }

    @Override
    public boolean isOwner(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.OWNER.getAuthority()));
    }

    @Override
    public boolean isManager(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.MANAGER.getAuthority()));
    }

    @Override
    public boolean isCustomer(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.CUSTOMER.getAuthority()));
    }

    @Override
    public boolean isMaster(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.MASTER.getAuthority()));
    }

    @Override
    public UserAddress findUserAddressById(String userAddressId) {
        return null;
    }
}

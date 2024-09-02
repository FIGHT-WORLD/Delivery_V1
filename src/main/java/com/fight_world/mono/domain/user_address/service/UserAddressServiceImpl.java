package com.fight_world.mono.domain.user_address.service;

import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.INTERNAL_SERVER_ERROR_USER_ADDRESS;
import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.INVALID_USER_AUTHORIZATION;
import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.NOT_FOUND_DELETED_USER;
import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.NOT_FOUND_USER_ADDRESS;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.UserRole;
import com.fight_world.mono.domain.user_address.dto.request.CreateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.request.UpdateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.response.CreateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.DeleteUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressListResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.UpdateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.exception.UserAddressException;
import com.fight_world.mono.domain.user_address.model.UserAddress;
import com.fight_world.mono.domain.user_address.repository.UserAddressRepository;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository userAddressRepository;

    @Transactional
    @Override
    public CreateUserAddressResponseDto createUserAddress(
            CreateUserAddressRequestDto requestDto,
            UserDetailsImpl userDetails) {

        isDeletedUser(userDetails);
        User user = userDetails.getUser();
        UserAddress userAddress = userAddressRepository.save(UserAddress.of(requestDto, user));

        return CreateUserAddressResponseDto.from(userAddress);
    }

    @Override
    public GetUserAddressResponseDto getUserAddress(
            String userAddressId,
            UserDetailsImpl userDetails) {

        UserAddress userAddress;

        if (verifyUserIsAdminAndNotDeleted(userDetails)) {
            userAddress = findUserAddressById(userAddressId);
        } else {
            userAddress = checkUserAddressIsDeleted(userAddressId);
            checkSameUser(userDetails, userAddress);
        }

        return GetUserAddressResponseDto.from(userAddress);
    }

    @Transactional
    @Override
    public UpdateUserAddressResponseDto updateUserAddress(
            String userAddressId,
            UpdateUserAddressRequestDto requestDto,
            UserDetailsImpl userDetails) {

        UserAddress userAddress;

        if (verifyUserIsAdminAndNotDeleted(userDetails)) {
            userAddress = findUserAddressById(userAddressId);
        } else {
            userAddress = checkUserAddressIsDeleted(userAddressId);
            checkSameUser(userDetails, userAddress);
        }
        userAddress.update(requestDto);

        return UpdateUserAddressResponseDto.from(userAddress);
    }

    @Override
    public Page<GetUserAddressListResponseDto> getUserAddressList(UserDetailsImpl userDetails,
            Pageable pageable) {

        if (verifyUserIsAdminAndNotDeleted(userDetails)) {

            return userAddressRepository
                    .findAllByUserId(userDetails.getUser().getId(), pageable)
                    .map(GetUserAddressListResponseDto::from);
        } else {

            return userAddressRepository
                    .findAllByUserIdAndDeletedAtIsNotNull(userDetails.getUser().getId(), pageable)
                    .map(GetUserAddressListResponseDto::from);
        }
    }

    @Transactional
    @Override
    public DeleteUserAddressResponseDto deleteUserAddress(
            String userAddressId,
            UserDetailsImpl userDetails) {

        UserAddress userAddress;

        if (verifyUserIsAdminAndNotDeleted(userDetails)) {
            userAddress = checkUserAddressIsDeleted(userAddressId);
        } else {
            userAddress = checkUserAddressIsDeleted(userAddressId);
            checkSameUser(userDetails, userAddress);
        }
        userAddress.deleteUserAddress(userDetails.getUser().getId());

        return DeleteUserAddressResponseDto.from(userAddress);
    }

    private UserAddress checkUserAddressIsDeleted(String userAddressId) {

        UserAddress userAddress = findUserAddressById(userAddressId);
        isDeletedAddress(userAddress);

        return userAddress;
    }

    private void isDeletedUser(UserDetailsImpl userDetails) {

        if (!userDetails.isAccountNonExpired()) {
            throw new UserAddressException(NOT_FOUND_DELETED_USER);
        }
    }

    private void checkSameUser(UserDetailsImpl userDetails, UserAddress userAddress) {

        if (userAddress.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new UserAddressException(INVALID_USER_AUTHORIZATION);
        }
    }

    private boolean isOwner(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.OWNER.getAuthority()));
    }

    private boolean isManager(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.MANAGER.getAuthority()));
    }

    private boolean isCustomer(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.CUSTOMER.getAuthority()));
    }

    private boolean isMaster(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.MASTER.getAuthority()));
    }

    private void isDeletedAddress(UserAddress userAddress) {

        if (userAddress.getDeletedAt() != null) {
            throw new UserAddressException(NOT_FOUND_USER_ADDRESS);
        }
    }

    @Override
    public UserAddress findUserAddressById(String userAddressId) {

        return userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new UserAddressException(NOT_FOUND_USER_ADDRESS));
    }

    private boolean verifyUserIsAdminAndNotDeleted(UserDetailsImpl userDetails) {

        isDeletedUser(userDetails);
        if (isMaster(userDetails) || isManager(userDetails)) {

            return true;
        } else if (isCustomer(userDetails) || isOwner(userDetails)) {

            return false;
        } else {
            throw new UserAddressException(INTERNAL_SERVER_ERROR_USER_ADDRESS);
        }
    }
}

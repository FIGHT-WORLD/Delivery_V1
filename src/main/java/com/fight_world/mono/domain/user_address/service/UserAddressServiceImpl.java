package com.fight_world.mono.domain.user_address.service;

import static com.fight_world.mono.domain.user_address.message.ExceptionMessage.FORBIDDEN_USER_AUTHORITY;
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
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository userAddressRepository;

    @Override
    public CreateUserAddressResponseDto createUserAddress(
            CreateUserAddressRequestDto requestDto,
            UserDetailsImpl userDetails) {

        isDeletedUser(userDetails);
        User user = userDetails.getUser();
        UserAddress savedUserAddress = UserAddress.of(requestDto, user);
        userAddressRepository.save(savedUserAddress);

        return CreateUserAddressResponseDto.from(savedUserAddress);
    }

    @Override
    public GetUserAddressResponseDto getUserAddress(
            String userAddressId,
            UserDetailsImpl userDetails) {

        isDeletedUser(userDetails);

        if (isOwner(userDetails) || isCustomer(userDetails)) {
            UserAddress gottenUserAddress = findUserAddressById(userAddressId);
            isDeletedAddress(gottenUserAddress);
            checkSameUser(userDetails, gottenUserAddress);

            return GetUserAddressResponseDto.from(gottenUserAddress);

        } else if (isManager(userDetails) || isMaster(userDetails)) {
            UserAddress gottenUserAddress = findUserAddressById(userAddressId);

            return GetUserAddressResponseDto.from(gottenUserAddress);
        }

        throw new UserAddressException(INTERNAL_SERVER_ERROR_USER_ADDRESS);
    }

    @Override
    public UpdateUserAddressResponseDto updateUserAddress(
            String userAddressId,
            UpdateUserAddressRequestDto requestDto,
            UserDetailsImpl userDetails) {

        isDeletedUser(userDetails);

        if (isOwner(userDetails) || isCustomer(userDetails)) {
            UserAddress updatedUserAddress = findUserAddressById(userAddressId);
            isDeletedAddress(updatedUserAddress);
            checkSameUser(userDetails, updatedUserAddress);
            updatedUserAddress.update(requestDto);

            return UpdateUserAddressResponseDto.from(updatedUserAddress);
        } else if (isManager(userDetails) || isMaster(userDetails)) {
            UserAddress updatedUserAddress = findUserAddressById(userAddressId);
            updatedUserAddress.update(requestDto);

            return UpdateUserAddressResponseDto.from(updatedUserAddress);
        }

        throw new UserAddressException(INTERNAL_SERVER_ERROR_USER_ADDRESS);
    }

    @Override
    public List<GetUserAddressListResponseDto> getUserAddressList(UserDetailsImpl userDetails) {

        if (isMaster(userDetails) || isManager(userDetails)) {

            return userAddressRepository
                    .findAll(Sort.by(Sort.DEFAULT_DIRECTION))
                    .stream()
                    .map(GetUserAddressListResponseDto::of)
                    .toList();
        } else if (isCustomer(userDetails) || isOwner(userDetails)) {

            return userAddressRepository
                    .findAllByUserId(userDetails.getUser().getId())
                    .stream()
                    .filter(userAddress -> userAddress.getDeletedAt() != null)
                    .map(GetUserAddressListResponseDto::of)
                    .toList();
        }

        throw new UserAddressException(FORBIDDEN_USER_AUTHORITY);

    }

    @Override
    public DeleteUserAddressResponseDto deleteUserAddress(
            String userAddressId,
            UserDetailsImpl userDetails) {

        isDeletedUser(userDetails);

        if (isOwner(userDetails) || isCustomer(userDetails)) {
            UserAddress deletedAddress = findUserAddressById(userAddressId);
            isDeletedAddress(deletedAddress);
            checkSameUser(userDetails, deletedAddress);
            deletedAddress.deleteUserAddress(userDetails.getUser().getId());
            userAddressRepository.save(deletedAddress);

            return DeleteUserAddressResponseDto.from(deletedAddress);

        } else if (isManager(userDetails) || isMaster(userDetails)) {
            UserAddress deletedAddress = findUserAddressById(userAddressId);
            isDeletedAddress(deletedAddress);
            deletedAddress.deleteUserAddress(userDetails.getUser().getId());
            userAddressRepository.save(deletedAddress);

            return DeleteUserAddressResponseDto.from(deletedAddress);
        }

        throw new UserAddressException(INTERNAL_SERVER_ERROR_USER_ADDRESS);
    }

    private static void isDeletedUser(UserDetailsImpl userDetails) {

        if (!userDetails.isAccountNonExpired()) {
            throw new UserAddressException(NOT_FOUND_DELETED_USER);
        }
    }

    private static void checkSameUser(UserDetailsImpl userDetails, UserAddress userAddress) {

        if (!Objects.equals(userAddress
                .getUser().getId(), userDetails.getUser().getId())) {
            throw new UserAddressException(INVALID_USER_AUTHORIZATION);
        }
    }

    private static boolean isOwner(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.OWNER.getAuthority()));
    }

    private static boolean isManager(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.MANAGER.getAuthority()));
    }

    private static boolean isCustomer(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.CUSTOMER.getAuthority()));
    }

    private static boolean isMaster(UserDetailsImpl userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(UserRole.MASTER.getAuthority()));
    }

    private static void isDeletedAddress(UserAddress userAddress) {

        if (userAddress.getDeletedAt() != null) {
            throw new UserAddressException(NOT_FOUND_USER_ADDRESS);
        }
    }

    @Override
    public UserAddress findUserAddressById(String userAddressId) {
        return userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new UserAddressException(NOT_FOUND_USER_ADDRESS));
    }
}

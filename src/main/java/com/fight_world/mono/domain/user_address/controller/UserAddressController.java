package com.fight_world.mono.domain.user_address.controller;

import static com.fight_world.mono.domain.user_address.message.SuccessMessage.CREATE_SUCCESS_USER_ADDRESS;
import static com.fight_world.mono.domain.user_address.message.SuccessMessage.DELETE_SUCCESS_USER_ADDRESS;
import static com.fight_world.mono.domain.user_address.message.SuccessMessage.GET_SUCCESS_USER_ADDRESS;
import static com.fight_world.mono.domain.user_address.message.SuccessMessage.GET_SUCCESS_USER_ADDRESS_LIST;
import static com.fight_world.mono.domain.user_address.message.SuccessMessage.SEARCH_SUCCESS_USER_ADDRESS;
import static com.fight_world.mono.domain.user_address.message.SuccessMessage.UPDATE_SUCCESS_USER_ADDRESS;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.user_address.dto.request.CreateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.request.UpdateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.response.CreateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.DeleteUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressListResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.SearchUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.UpdateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.service.UserAddressServiceImpl;
import com.fight_world.mono.global.aop.page.PageSizeLimit;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressServiceImpl userAddressService;

    /*
    주소 생성
     */
    @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @PostMapping("/user-addresses")
    public ResponseEntity<? extends CommonResponse> createUserAddress(
            @RequestBody CreateUserAddressRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CreateUserAddressResponseDto responseDto = userAddressService.createUserAddress(requestDto,
                userDetails);

        return ResponseEntity
                .status(CREATE_SUCCESS_USER_ADDRESS.getHttpStatus())
                .body(success(CREATE_SUCCESS_USER_ADDRESS.getMessage(), responseDto));
    }

    /*
    주소 전체 조회 (썸네일만)
     */
    @PageSizeLimit
    @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @GetMapping("/user-addresses")
    public ResponseEntity<? extends CommonResponse> getAllUserAddresses(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

        Page<GetUserAddressListResponseDto> responseDto =
                userAddressService.getUserAddressList(userDetails, pageable);

        return ResponseEntity
                .status(GET_SUCCESS_USER_ADDRESS_LIST.getHttpStatus())
                .body(success(GET_SUCCESS_USER_ADDRESS_LIST.getMessage(), responseDto));
    }

    /*
    주소 상세 조회
     */
    @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @GetMapping("/user-addresses/{userAddressId}")
    public ResponseEntity<? extends CommonResponse> getUserAddress(
            @PathVariable("userAddressId") String id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        GetUserAddressResponseDto responseDto = userAddressService.getUserAddress(id, userDetails);

        return ResponseEntity
                .status(GET_SUCCESS_USER_ADDRESS.getHttpStatus())
                .body(success(GET_SUCCESS_USER_ADDRESS.getMessage(), responseDto));
    }

    /*
    주소 수정
     */
    @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @PatchMapping("/user-addresses/{userAddressId}")
    public ResponseEntity<? extends CommonResponse> updateUserAddress(
            @PathVariable("userAddressId") String id,
            @RequestBody UpdateUserAddressRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        UpdateUserAddressResponseDto responseDto = userAddressService.updateUserAddress(id,
                requestDto, userDetails);

        return ResponseEntity
                .status(UPDATE_SUCCESS_USER_ADDRESS.getHttpStatus())
                .body(success(UPDATE_SUCCESS_USER_ADDRESS.getMessage(), responseDto));
    }

    /*
    주소 삭제
     */
    @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @DeleteMapping("/user-addresses/{userAddressId}")
    public ResponseEntity<? extends CommonResponse> deleteUserAddress(
            @PathVariable("userAddressId") String id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        DeleteUserAddressResponseDto responseDto =
                userAddressService.deleteUserAddress(id, userDetails);

        return ResponseEntity
                .status(DELETE_SUCCESS_USER_ADDRESS.getHttpStatus())
                .body(success(DELETE_SUCCESS_USER_ADDRESS.getMessage(), responseDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @PageSizeLimit
    @RequestMapping("/user-addresses/search")
    @Transactional(readOnly = true)
    public ResponseEntity<? extends CommonResponse> searchUserAddresses(
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @RequestParam(value = "query") String query,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Page<SearchUserAddressResponseDto> responseDtos = userAddressService.searchUserAddress(
                pageable,
                query,
                userDetails);

        return ResponseEntity
                .status(SEARCH_SUCCESS_USER_ADDRESS.getHttpStatus())
                .body(success(SEARCH_SUCCESS_USER_ADDRESS.getMessage(), responseDtos));
    }
}

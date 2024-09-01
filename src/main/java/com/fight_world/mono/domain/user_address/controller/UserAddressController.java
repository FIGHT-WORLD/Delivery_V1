package com.fight_world.mono.domain.user_address.controller;

import static com.fight_world.mono.domain.user_address.message.SuccessMessage.CREATE_SUCCESS_USER_ADDRESS;
import static com.fight_world.mono.domain.user_address.message.SuccessMessage.GET_SUCCESS_USER_ADDRESS_LIST;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.user_address.dto.request.CreateUserAddressRequestDto;
import com.fight_world.mono.domain.user_address.dto.response.CreateUserAddressResponseDto;
import com.fight_world.mono.domain.user_address.dto.response.GetUserAddressListResponseDto;
import com.fight_world.mono.domain.user_address.service.UserAddressServiceImpl;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressServiceImpl userAddressService;

    /*
    주소 생성
    접근 권한 :: CUSTOMER, OWNER, MANAGER, ADMIN
     */
    @PostMapping("/user-addresses")
    public ResponseEntity<? extends CommonResponse> createUserAddress(
            @RequestBody CreateUserAddressRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        CreateUserAddressResponseDto responseDto = userAddressService.createUserAddress(requestDto,
                userDetails);

        return ResponseEntity
                .status(CREATE_SUCCESS_USER_ADDRESS.getHttpStatus())
                .body(success(CREATE_SUCCESS_USER_ADDRESS.getMessage(), responseDto));
    }

    /*
    주소 전체 조회 (썸네일만)
    접근 권한 :: CUSTOMER, OWNER, MANAGER, ADMIN
     */
    @GetMapping("/user-addresses")
    public ResponseEntity<? extends CommonResponse> getUserAddressesList(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        List<GetUserAddressListResponseDto> responseDto =
                userAddressService.getUserAddressList(userDetails);

        return ResponseEntity
                .status(GET_SUCCESS_USER_ADDRESS_LIST.getHttpStatus())
                .body(success(GET_SUCCESS_USER_ADDRESS_LIST.getMessage(), responseDto));
    }

    /*
    주소 상세 조회
    접근 권한 :: CUSTOMER, OWNER, MANAGER, ADMIN
     */
    @GetMapping("/user-addresses/{userAddressId}")
    public ResponseEntity<?> getUserAddress(@PathVariable("userAddressId") String id) {

        return null;
    }

    /*
    주소 수정
    접근 권한 :: CUSTOMER, OWNER, MANAGER, ADMIN
     */
    @PatchMapping("/user-addresses/{userAddressId}")
    public ResponseEntity<?> updateUserAddress(@PathVariable("userAddressId") String id) {

        return null;
    }

    /*
    주소 삭제
    접근 권한 :: CUSTOMER, OWNER, MANAGER, ADMIN
     */
    @DeleteMapping("/user-addresses/{userAddressId}")
    public ResponseEntity<?> deleteUserAddress(
            @PathVariable("userAddressId") String id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        userAddressService.deleteUserAddress(id, userDetails);

        return null;
    }
}

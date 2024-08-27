package com.fight_world.mono.domain.user_address.controller;

import com.fight_world.mono.domain.user_address.service.UserAddressService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    /*
    주소 생성
    접근 권한 :: CUSTOMER, OWNER, MANAGER, ADMIN
     */
    @PostMapping("/user-addresses")
    public ResponseEntity<?> createUserAddress() {

        return null;
    }

    /*
    주소 전체 조회 (썸네일만)
    접근 권한 :: CUSTOMER, OWNER, MANAGER, ADMIN
     */
    @GetMapping("/user-addresses")
    public ResponseEntity<List<?>> getUserAddresses() {

        return null;
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
    public ResponseEntity<?> patchUserAddress(@PathVariable("userAddressId") String id) {

        return null;
    }

    /*
    주소 삭제
    접근 권한 :: CUSTOMER, OWNER, MANAGER, ADMIN
     */
    @DeleteMapping("/user-addresses/{userAddressId}")
    public ResponseEntity<?> deleteUserAddress(@PathVariable("userAddressId") String id) {

        return null;
    }
}

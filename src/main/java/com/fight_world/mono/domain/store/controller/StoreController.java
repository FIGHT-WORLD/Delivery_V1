package com.fight_world.mono.domain.store.controller;

import static com.fight_world.mono.domain.store.message.SuccessMessage.SUCCESS_GET_ONE_STORE;
import static com.fight_world.mono.domain.store.message.SuccessMessage.SUCCESS_REGISTER_STORE;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.store.dto.request.StoreRegisterRequestDto;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 등록 api
     */
    @PostMapping("/")
    public ResponseEntity<? extends CommonResponse> registerStore(
           @Valid @RequestBody StoreRegisterRequestDto requestDto,
           @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.status(SUCCESS_REGISTER_STORE.getHttpStatus())
                             .body(success(SUCCESS_REGISTER_STORE.getMessage(), storeService.registerStore(userDetails, requestDto)));

    }

    /**
     * 가게 상세조회 api
     */
    @GetMapping("/{storeId}")
    public ResponseEntity<? extends CommonResponse> getStore(@PathVariable(name = "storeId") String storeId) {

        return ResponseEntity.status(SUCCESS_GET_ONE_STORE.getHttpStatus())
                .body(success(SUCCESS_GET_ONE_STORE.getMessage(), storeService.getStore(storeId)));

    }
}

package com.fight_world.mono.domain.store.controller;

import static com.fight_world.mono.domain.store.message.SuccessMessage.SUCCESS_GET_STORE_LIST;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 등록 api
     * @param requestDto
     * @param userDetails
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<? extends CommonResponse> registerStore(
           @Valid @RequestBody StoreRegisterRequestDto requestDto,
           @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.status(SUCCESS_REGISTER_STORE.getHttpStatus())
                             .body(success(SUCCESS_REGISTER_STORE.getMessage(), storeService.registerStore(userDetails, requestDto)));

    }
}

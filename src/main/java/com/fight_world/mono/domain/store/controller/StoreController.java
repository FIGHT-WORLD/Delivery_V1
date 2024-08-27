package com.fight_world.mono.domain.store.controller;

import static com.fight_world.mono.domain.store.message.SuccessMessage.SUCCESS_GET_STORE_LIST;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/")
    public ResponseEntity<? extends CommonResponse> getStores() {
        // logic
        return ResponseEntity.status(SUCCESS_GET_STORE_LIST.getHttpStatus())
                             .body(success(SUCCESS_GET_STORE_LIST.getMessage()));
    }
}

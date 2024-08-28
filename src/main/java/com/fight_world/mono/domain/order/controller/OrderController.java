package com.fight_world.mono.domain.order.controller;

import static com.fight_world.mono.domain.order.message.SuccessMessage.CREATED_ORDER;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.service.OrderService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders")
    public ResponseEntity<? extends CommonResponse> createOrder(
            @RequestBody OrderCreateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(CREATED_ORDER.getStatus())
                             .body(success(CREATED_ORDER.getMessage(), orderService.createOrder(userDetails, requestDto)));
    }
}

package com.fight_world.mono.domain.order.controller;

import static com.fight_world.mono.domain.order.message.SuccessMessage.CREATED_ORDER;
import static com.fight_world.mono.domain.order.message.SuccessMessage.DELETE_ORDER;
import static com.fight_world.mono.domain.order.message.SuccessMessage.GET_ORDER;
import static com.fight_world.mono.domain.order.message.SuccessMessage.GET_ORDERS;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.service.OrderService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<? extends CommonResponse> createOrder(
            @RequestBody OrderCreateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(CREATED_ORDER.getStatus())
                             .body(success(CREATED_ORDER.getMessage(), orderService.createOrder(userDetails, requestDto)));
    }

    @GetMapping("/orders")
    public ResponseEntity<? extends CommonResponse> getOrders(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(GET_ORDERS.getStatus())
                             .body(success(GET_ORDERS.getMessage(), orderService.getOrders(userDetails)));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<? extends CommonResponse> getOrder(
            @PathVariable String orderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(GET_ORDER.getStatus())
                             .body(success(GET_ORDER.getMessage(), orderService.getOrder(userDetails, orderId)));
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<? extends CommonResponse> deleteOrderFromUser(
            @PathVariable String orderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        orderService.deleteOrderFromUser(userDetails, orderId);

        return ResponseEntity.status(DELETE_ORDER.getStatus())
                             .body(success(DELETE_ORDER.getMessage()));
    }
}

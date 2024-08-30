package com.fight_world.mono.domain.order.controller;

import static com.fight_world.mono.domain.order.message.SuccessMessage.CREATED_ORDER;
import static com.fight_world.mono.domain.order.message.SuccessMessage.DELETE_ORDER;
import static com.fight_world.mono.domain.order.message.SuccessMessage.GET_ORDER;
import static com.fight_world.mono.domain.order.message.SuccessMessage.GET_ORDERS;
import static com.fight_world.mono.domain.order.message.SuccessMessage.UPDATE_ORDER_TO_COOKING;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.order.dto.request.OrderCreateRequestDto;
import com.fight_world.mono.domain.order.service.OrderService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @RequestParam(required = false) String store_name,
            @RequestParam(required = false) String menu_name
    ) {

        return ResponseEntity.status(GET_ORDERS.getStatus())
                             .body(success(GET_ORDERS.getMessage(), orderService.getOrders(userDetails, pageable, store_name, menu_name)));
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

    @PatchMapping("/orders/{orderId}/cooking")
    public ResponseEntity<? extends CommonResponse> updateOrderToCheck(
            @PathVariable String orderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        orderService.updateOrderToCooking(orderId, userDetails);

        return ResponseEntity.status(UPDATE_ORDER_TO_COOKING.getStatus())
                .body(success(UPDATE_ORDER_TO_COOKING.getMessage()));
    }

    @GetMapping("/store/{storeId}/orders")
    public ResponseEntity<? extends CommonResponse> getStoreOrders(
            @PathVariable String storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Long user_id
    ) {

        return ResponseEntity.status(UPDATE_ORDER_TO_COOKING.getStatus())
                .body(success(UPDATE_ORDER_TO_COOKING.getMessage(), orderService.getStoreOrders(storeId, userDetails, pageable, user_id)));
    }
}

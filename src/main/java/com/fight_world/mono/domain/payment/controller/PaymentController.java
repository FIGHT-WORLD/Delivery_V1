package com.fight_world.mono.domain.payment.controller;

import static com.fight_world.mono.domain.payment.message.SuccessMessage.*;
import static com.fight_world.mono.global.response.SuccessResponse.*;

import com.fight_world.mono.domain.payment.dto.request.PaymentCreateRequestDto;
import com.fight_world.mono.domain.payment.service.PaymentService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<? extends CommonResponse> createPayment(
            @RequestBody PaymentCreateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(CREATED_PAYMENT.getStatus())
                .body(success(CREATED_PAYMENT.getMessage(), paymentService.createPayment(userDetails, requestDto)));
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<? extends CommonResponse> getPayment(
            @PathVariable String paymentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(GET_PAYMENT.getStatus())
                .body(success(GET_PAYMENT.getMessage(), paymentService.getPayment(userDetails, paymentId)));
    }

    @GetMapping("/payments")
    public ResponseEntity<? extends CommonResponse> getPayments(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(GET_PAYMENTS.getStatus())
                .body(success(GET_PAYMENTS.getMessage(), paymentService.getPayments(userDetails)));
    }

}

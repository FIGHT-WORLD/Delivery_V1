package com.fight_world.mono.domain.deliveryArea.controller;

import static com.fight_world.mono.domain.deliveryArea.message.SuccessMessage.SUCCESS_ADD_DELIVERY_AREA;
import static com.fight_world.mono.domain.deliveryArea.message.SuccessMessage.SUCCESS_GET_DELIVERY_AREA;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.deliveryArea.dto.request.RegisterDeliveryAreaRequestDto;
import com.fight_world.mono.domain.deliveryArea.service.DeliveryAreaService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DeliveryAreaController {

    private final DeliveryAreaService deliveryAreaService;

    /**
     * 배달 가능 지역 등록 api
     */
    @PreAuthorize("hasAnyRole('ROLE_OWNER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @PostMapping("/stores/delivery-area")
    public ResponseEntity<? extends CommonResponse> registerDeliveryArea(
            @RequestBody RegisterDeliveryAreaRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(SUCCESS_ADD_DELIVERY_AREA.getHttpStatus())
                .body(success(SUCCESS_ADD_DELIVERY_AREA.getMessage(),
                        deliveryAreaService.registerDeliveryArea(userDetails, requestDto)));
    }

    /**
     * 배달 가능 지역 조회 api
     */
    @GetMapping("/stores/delivery-area")
    public ResponseEntity<? extends CommonResponse> getDeliveryArea(
            @RequestParam(name = "storeId") String storeId) {

        return ResponseEntity.status(SUCCESS_GET_DELIVERY_AREA.getHttpStatus())
                .body(success(SUCCESS_GET_DELIVERY_AREA.getMessage(),
                        deliveryAreaService.getDeliveryArea(storeId)));
    }

}

package com.fight_world.mono.domain.store_category.controller;

import static com.fight_world.mono.domain.store_category.message.SuccessMessage.SUCCESS_ADD_STORE_CATEGORY;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryAddRequestDto;
import com.fight_world.mono.domain.store_category.service.StoreCategoryService;
import com.fight_world.mono.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store-category")
public class StoreCategoryController {

    private final StoreCategoryService storeCategoryService;

    /**
     * 카테고리 추가 api
     */
    @PostMapping("/")
    public ResponseEntity<? extends CommonResponse> addStoreCategory(@Valid @RequestBody StoreCategoryAddRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.status(SUCCESS_ADD_STORE_CATEGORY.getHttpStatus())
                .body(success(SUCCESS_ADD_STORE_CATEGORY.getMessage(), storeCategoryService.addStoreCategory(userDetails, requestDto)));
    }
}

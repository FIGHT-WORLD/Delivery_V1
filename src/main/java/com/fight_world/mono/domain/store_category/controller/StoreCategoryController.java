package com.fight_world.mono.domain.store_category.controller;

import static com.fight_world.mono.domain.store_category.message.SuccessMessage.SUCCESS_ADD_STORE_CATEGORY;
import static com.fight_world.mono.domain.store_category.message.SuccessMessage.SUCCESS_CHANGE_STORE_CATEGORY;
import static com.fight_world.mono.domain.store_category.message.SuccessMessage.SUCCESS_DELETE_STORE_CATEGORY;
import static com.fight_world.mono.domain.store_category.message.SuccessMessage.SUCCESS_GET_STORE_CATEGORY_LIST;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryAddRequestDto;
import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryModifyRequestDto;
import com.fight_world.mono.domain.store_category.service.StoreCategoryService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @PostMapping("")
    public ResponseEntity<? extends CommonResponse> addStoreCategory(
            @Valid @RequestBody StoreCategoryAddRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(SUCCESS_ADD_STORE_CATEGORY.getHttpStatus())
                .body(success(SUCCESS_ADD_STORE_CATEGORY.getMessage(),
                        storeCategoryService.addStoreCategory(userDetails, requestDto)));
    }

    /**
     * 카테고리 수정 api
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<? extends CommonResponse> modifyStoreCategory(
            @PathVariable(name = "categoryId") String categoryId,
            @Valid @RequestBody StoreCategoryModifyRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(SUCCESS_CHANGE_STORE_CATEGORY.getHttpStatus())
                .body(success(SUCCESS_CHANGE_STORE_CATEGORY.getMessage(),
                        storeCategoryService.modifyCategory(userDetails, categoryId, requestDto)));
    }

    /**
     * 카테고리 삭제 api
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<? extends CommonResponse> deleteStoreCategory(
            @PathVariable(name = "categoryId") String categoryId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        storeCategoryService.deleteCategory(userDetails, categoryId);

        return ResponseEntity.status(SUCCESS_DELETE_STORE_CATEGORY.getHttpStatus())
                .body(success(SUCCESS_DELETE_STORE_CATEGORY.getMessage()));
    }

    /**
     * 카테고리 전체 조회 api
     */
    @GetMapping("")
    public ResponseEntity<? extends CommonResponse> getStoreCategories() {

        return ResponseEntity.status(SUCCESS_GET_STORE_CATEGORY_LIST.getHttpStatus())
                .body(success(SUCCESS_GET_STORE_CATEGORY_LIST.getMessage(), storeCategoryService.getStoreCategories()));
    }
}

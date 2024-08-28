package com.fight_world.mono.domain.store_category.service;

import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryAddRequestDto;
import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryModifyRequestDto;
import com.fight_world.mono.domain.store_category.dto.response.StoreCategoryResponseDto;
import com.fight_world.mono.domain.store_category.model.StoreCategory;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;

public interface StoreCategoryService {

    // 카테고리 추가
    StoreCategoryResponseDto addStoreCategory(UserDetailsImpl userDetails,
            StoreCategoryAddRequestDto requestDto);

    // 카테고리 조회
    List<StoreCategoryResponseDto> getStoreCategories();

    // 카테고리 수정
    StoreCategoryResponseDto modifyCategory(UserDetailsImpl userDetails, String categoryId,
            StoreCategoryModifyRequestDto requestDto);

    // 카테고리 삭제
    void deleteCategory(UserDetailsImpl userDetails, String categoryId);

    StoreCategory findById(String id);
}

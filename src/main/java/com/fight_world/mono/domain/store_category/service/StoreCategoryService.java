package com.fight_world.mono.domain.store_category.service;

import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryAddRequestDto;
import com.fight_world.mono.domain.store_category.dto.response.StoreCategoryResponseDto;
import com.fight_world.mono.domain.store_category.model.StoreCategory;
import org.springframework.security.core.userdetails.UserDetails;

public interface StoreCategoryService {

    // 카테고리 추가
    StoreCategoryResponseDto addStoreCategory(UserDetails userDetails, StoreCategoryAddRequestDto requestDto);

    // 카테고리 조회

    // 카테고리 수정

    // 카테고리 삭제

    StoreCategory findById(String id);
}

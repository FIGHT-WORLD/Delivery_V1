package com.fight_world.mono.domain.store.service;

import com.fight_world.mono.domain.store.dto.request.ModifyStoreRequestDto;
import com.fight_world.mono.domain.store.dto.request.RegisterStoreRequestDto;
import com.fight_world.mono.domain.store.dto.request.ChangeStoreStatusRequestDto;
import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.global.security.UserDetailsImpl;
import org.springframework.data.domain.Page;

public interface StoreService {

    // 가게 등록
    StoreResponseDto registerStore(UserDetailsImpl userDetails, RegisterStoreRequestDto requestDto);

    // 가게 상세 조회
    StoreResponseDto getStore(String storeId);

    // 가게 목록 조회
    Page<StoreResponseDto> getStores(String storeCategoryId, int page, int size);

    // 가게 정보 수정
    StoreResponseDto modifyStore(UserDetailsImpl userDetails, String storeId,
            ModifyStoreRequestDto requestDto);

    // 가게 주문 가능 여부 상태 변경
    void changeStoreStatus(UserDetailsImpl userDetails, String storeId,
            ChangeStoreStatusRequestDto requestDto);

    // 가게 삭제
    void deleteStore(UserDetailsImpl userDetails, String storeId);

    // 가게 검색
    Page<StoreResponseDto> searchStores(int page, int size, String query);

    // id로 가게 검색하기
    Store findById(String storeId);

    // 가게 주인인지 확인하기
    void checkIsStoreOwner(UserDetailsImpl userDetails, Store store);
}

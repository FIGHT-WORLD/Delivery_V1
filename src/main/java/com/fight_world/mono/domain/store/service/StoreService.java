package com.fight_world.mono.domain.store.service;

import com.fight_world.mono.domain.store.dto.request.StoreRegisterRequestDto;
import com.fight_world.mono.domain.store.dto.request.StoreStatusRequestDto;
import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface StoreService {

    // 가게 등록
    StoreResponseDto registerStore(UserDetails userDetails, StoreRegisterRequestDto requestDto);

    // 가게 상세 조회
    StoreResponseDto getStore(String storeId);

    // 가게 목록 조회

    // 가게 정보 수정

    // 가게 주문 가능 여부 상태 변경
    void changeStoreStatus(UserDetails userDetails, String storeId, StoreStatusRequestDto requestDto);

    // 가게 삭제
    void deleteStore(UserDetails userDetails, String storeId);

    // 가게 검색

    // id로 가게 검색하기
    Store findById(String storeId);

    // 가게 주인인지 확인하기
    User checkIsStoreOwner(UserDetails userDetails, Store store);
}

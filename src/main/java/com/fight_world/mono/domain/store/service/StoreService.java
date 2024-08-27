package com.fight_world.mono.domain.store.service;

import com.fight_world.mono.domain.store.dto.request.StoreRegisterRequestDto;
import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface StoreService {

    // 가게 등록
    StoreResponseDto registerStore(UserDetails userDetails, StoreRegisterRequestDto requestDto);

    // 가게 상세 조회

    // 가게 목록 조회

    // 가게 정보 수정

    // 가게 주문 가능 여부 상태 변경

    // 가게 삭제

    // 가게 검색

    // id로 가게 검색하기
//    Store findById(String storeId);

}

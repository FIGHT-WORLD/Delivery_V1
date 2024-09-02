package com.fight_world.mono.domain.store.repository;

import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreQueryRepository {

    Page<StoreResponseDto> findStoresByDongeupmyunCodeAndCategory(String dongeupmyunCode,
            String categoryName, Pageable pageable);

}

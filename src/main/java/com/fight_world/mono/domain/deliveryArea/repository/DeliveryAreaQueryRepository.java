package com.fight_world.mono.domain.deliveryArea.repository;

import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryAreaQueryRepository {

    Page<StoreResponseDto> findStoresByDongeupmyunCodeAndCategory(String dongeupmyunCode,
            String categoryName, Pageable pageable);
}

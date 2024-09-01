package com.fight_world.mono.domain.deliveryArea.service;

import com.fight_world.mono.domain.deliveryArea.dto.request.RegisterDeliveryAreaRequestDto;
import com.fight_world.mono.domain.deliveryArea.dto.response.DeliveryAreaResponseDto;
import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryAreaService {

    List<DeliveryAreaResponseDto> registerDeliveryArea(UserDetailsImpl userDetails,
            RegisterDeliveryAreaRequestDto requestDto);

    List<DeliveryAreaResponseDto> getDeliveryArea(String storeId);

    Page<StoreResponseDto> getDeliveryAvailableStores(String areaId, String storeCategory,
            Pageable pageable);
}

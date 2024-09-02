package com.fight_world.mono.domain.deliveryArea.service;

import com.fight_world.mono.domain.deliveryArea.dto.request.RegisterDeliveryAreaRequestDto;
import com.fight_world.mono.domain.deliveryArea.dto.response.DeliveryAreaResponseDto;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;

public interface DeliveryAreaService {

    List<DeliveryAreaResponseDto> registerDeliveryArea(UserDetailsImpl userDetails,
            RegisterDeliveryAreaRequestDto requestDto);

    List<DeliveryAreaResponseDto> getDeliveryArea(String storeId);
}

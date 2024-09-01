package com.fight_world.mono.domain.deliveryArea.service;

import com.fight_world.mono.domain.address.model.AddressDongeupmyun;
import com.fight_world.mono.domain.address.service.AddressService;
import com.fight_world.mono.domain.deliveryArea.dto.request.RegisterDeliveryAreaRequestDto;
import com.fight_world.mono.domain.deliveryArea.dto.response.DeliveryAreaResponseDto;
import com.fight_world.mono.domain.deliveryArea.model.DeliveryArea;
import com.fight_world.mono.domain.deliveryArea.repository.DeliveryAreaRepository;
import com.fight_world.mono.domain.store.exception.StoreException;
import com.fight_world.mono.domain.store.message.ExceptionMessage;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.UserRole;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryAreaServiceImpl implements DeliveryAreaService {

    private final DeliveryAreaRepository deliveryAreaRepository;
    private final UserService userService;
    private final StoreService storeService;
    private final AddressService addressService;


    @Override
    public List<DeliveryAreaResponseDto> registerDeliveryArea(UserDetailsImpl userDetails,
            RegisterDeliveryAreaRequestDto requestDto) {

        List<DeliveryArea> saveDeliveryAreas = new ArrayList<>();
        Store store = storeService.findById(requestDto.storeId());

        checkAuthority(userDetails, store);

        for (String dongCode : requestDto.areaId()) {
            AddressDongeupmyun addressDongeupmyun = addressService.findByDongeupmyunCode(dongCode);

            DeliveryArea deliveryArea = DeliveryArea.of(store, addressDongeupmyun);
            saveDeliveryAreas.add(deliveryArea);
        }

        return deliveryAreaRepository.saveAll(saveDeliveryAreas)
                .stream()
                .map(DeliveryAreaResponseDto::from)
                .collect(Collectors.toList());
    }

    public void checkAuthority(UserDetailsImpl userDetails, Store store) {

        if (!checkIsAdmin(userDetails)) {
            checkIsStoreOwner(userDetails, store);
        }
    }

    public boolean checkIsAdmin(UserDetailsImpl userDetails) {

        User user = userService.findById(userDetails.getUserId());

        return user.getRole().equals(UserRole.MANAGER) || user.getRole().equals(UserRole.MASTER);
    }

    public void checkIsStoreOwner(UserDetailsImpl userDetails, Store store) {

        User user = userService.findById(userDetails.getUserId());

        if (!store.getUser().equals(user)) {
            throw new StoreException(ExceptionMessage.STORE_UNAUTHORIZED);
        }
    }
}

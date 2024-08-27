package com.fight_world.mono.domain.store.service;

import com.fight_world.mono.domain.store.dto.request.StoreRegisterRequestDto;
import com.fight_world.mono.domain.store.dto.request.StoreStatusRequestDto;
import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import com.fight_world.mono.domain.store.exception.StoreException;
import com.fight_world.mono.domain.store.message.ExceptionMessage;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.model.constant.StoreStatus;
import com.fight_world.mono.domain.store.repository.StoreRepository;
import com.fight_world.mono.domain.store_category.model.StoreCategory;
import com.fight_world.mono.domain.store_category.service.StoreCategoryService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;
    private final StoreCategoryService storeCategoryService;

    // TODO:  테스트 필요
    @Override
    public StoreResponseDto registerStore(UserDetails userDetails, StoreRegisterRequestDto requestDto) {

        User user = null; // userService.findById(userDetails.getUser().getId());

        StoreCategory storeCategory = storeCategoryService.findById(requestDto.storeCategoryId());

        Store savedStore = storeRepository.save(Store.of(requestDto, storeCategory, user));

        return StoreResponseDto.of(savedStore);
    }

    // TODO:  테스트 필요
    @Override
    public StoreResponseDto getStore(String storeId) {

        Store store = findById(storeId);

        return StoreResponseDto.of(store);
    }

    @Override
    public void changeStoreStatus(UserDetails userDetails, String storeId, StoreStatusRequestDto requestDto) {

        Store store = findById(storeId);

        checkIsStoreOwner(userDetails, store);

        store.changeStatus(StoreStatus.valueOf(requestDto.status()));
    }

    @Override
    public void deleteStore(UserDetails userDetails, String storeId) {

        Store store = findById(storeId);

        User user = checkIsStoreOwner(userDetails, store);

        store.deleteStore(user.getId());

    }

    @Override
    public Store findById(String storeId) {

        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ExceptionMessage.STORE_NOT_FOUND));
    }

    public User checkIsStoreOwner(UserDetails userDetails, Store store) {

        User user = null; // userService.findById(userDetails.getUser().getId());

        if(!store.getUser().equals(user)) {
            throw new StoreException(ExceptionMessage.STORE_UNAUTHORIZED);
        }

        return user;
    }
}

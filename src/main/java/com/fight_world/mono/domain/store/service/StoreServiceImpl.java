package com.fight_world.mono.domain.store.service;

import com.fight_world.mono.domain.store.dto.request.StoreModifyRequestDto;
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

    // 가게 등록
    @Override
    public StoreResponseDto registerStore(UserDetails userDetails,
            StoreRegisterRequestDto requestDto) {

        User user = null; // userService.findById(userDetails.getUser().getId());

        StoreCategory storeCategory = storeCategoryService.findById(requestDto.storeCategoryId());

        Store savedStore = storeRepository.save(Store.of(requestDto, storeCategory, user));

        return StoreResponseDto.of(savedStore);
    }

    // 가게 상세 조회
    @Override
    public StoreResponseDto getStore(String storeId) {

        Store store = findById(storeId);

        return StoreResponseDto.of(store);
    }

    // 가게 정보 수정
    @Override
    public StoreResponseDto modifyStore(UserDetails userDetails, String storeId,
            StoreModifyRequestDto requestDto) {

        Store store = findById(storeId);

        checkAuthority(userDetails, store);

        if (!requestDto.name().equals(store.getName())) {
            if (storeRepository.existsByName(requestDto.name())) {
                throw new StoreException(ExceptionMessage.STORE_NAME_ALREADY_EXIST);
            }
        }

        StoreCategory storeCategory = storeCategoryService.findById(requestDto.storeCategoryId());

        store.modifyStore(requestDto, storeCategory);
        storeRepository.save(store);

        return StoreResponseDto.of(store);
    }

    // 가게 주문 가능 상태 변경
    @Override
    public void changeStoreStatus(UserDetails userDetails, String storeId,
            StoreStatusRequestDto requestDto) {

        Store store = findById(storeId);

        checkAuthority(userDetails, store);

        store.changeStatus(StoreStatus.valueOf(requestDto.status()));
        storeRepository.save(store);
    }

    //가게 삭제
    @Override
    public void deleteStore(UserDetails userDetails, String storeId) {

        Store store = findById(storeId);

        checkIsStoreOwner(userDetails, store);

        store.deleteStore(store.getUser().getId());
        storeRepository.save(store);
    }

    @Override
    public Store findById(String storeId) {

        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ExceptionMessage.STORE_NOT_FOUND));
    }

    public void checkAuthority(UserDetails userDetails, Store store) {

        if (!checkIsAdmin(userDetails)) {
            checkIsStoreOwner(userDetails, store);
        }
    }

    public boolean checkIsAdmin(UserDetails userDetails) {

        User user = null; // userService.findById(userDetails.getUser().getId());

        if (user.getRole().equals("MANAGER") || user.getRole().equals("MASTER")) {
            return true;
        }

        return false;
    }

    public void checkIsStoreOwner(UserDetails userDetails, Store store) {

        User user = null; // userService.findById(userDetails.getUser().getId());

        if (!store.getUser().equals(user)) {
            throw new StoreException(ExceptionMessage.STORE_UNAUTHORIZED);
        }

    }
}

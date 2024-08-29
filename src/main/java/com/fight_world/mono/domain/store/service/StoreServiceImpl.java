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
import com.fight_world.mono.domain.user.model.UserRole;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;
    private final StoreCategoryService storeCategoryService;

    // 가게 등록
    @Override
    @Transactional
    public StoreResponseDto registerStore(UserDetailsImpl userDetails,
            StoreRegisterRequestDto requestDto) {

        User user = userService.findById(userDetails.getUser().getId());

        StoreCategory storeCategory = storeCategoryService.findById(requestDto.storeCategoryId());

        Store savedStore = storeRepository.save(Store.of(requestDto, storeCategory, user));

        return StoreResponseDto.of(savedStore);
    }

    // 가게 상세 조회
    @Override
    @Transactional(readOnly = true)
    public StoreResponseDto getStore(String storeId) {

        Store store = findById(storeId);

        return StoreResponseDto.of(store);
    }

    // 가게 목록 조회 (페이징)
    @Override
    @Transactional(readOnly = true)
    public Page<StoreResponseDto> getStores(String storeCategoryId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Store> stores = null;

        if(storeCategoryId.isBlank()) {
            stores = storeRepository.findAll(pageable);
        } else {
            storeCategoryService.findById(storeCategoryId);
            stores = storeRepository.findAllByStoreCategoryId(storeCategoryId, pageable);

        }

        return stores.map(StoreResponseDto::of);
    }

    // 가게 검색 (페이징)
    @Override
    @Transactional(readOnly = true)
    public Page<StoreResponseDto> searchStores(int page, int size, String query) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Store> stores = storeRepository.findByNameContaining(query, pageable);

        return stores.map(StoreResponseDto::of);
    }

    // 가게 정보 수정
    @Override
    @Transactional
    public StoreResponseDto modifyStore(UserDetailsImpl userDetails, String storeId,
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

        return StoreResponseDto.of(store);
    }

    // 가게 주문 가능 상태 변경
    @Override
    @Transactional
    public void changeStoreStatus(UserDetailsImpl userDetails, String storeId,
            StoreStatusRequestDto requestDto) {

        Store store = findById(storeId);

        checkAuthority(userDetails, store);

        store.changeStatus(StoreStatus.valueOf(requestDto.status()));
    }

    //가게 삭제
    @Override
    @Transactional
    public void deleteStore(UserDetailsImpl userDetails, String storeId) {

        Store store = findById(storeId);

        checkIsStoreOwner(userDetails, store);

        store.deleteStore(store.getUser().getId());
    }

    @Override
    public Store findById(String storeId) {

        return storeRepository.findByIdAndDeletedAtIsNull(storeId)
                .orElseThrow(() -> new StoreException(ExceptionMessage.STORE_NOT_FOUND));
    }

    public void checkAuthority(UserDetailsImpl userDetails, Store store) {

        if (!checkIsAdmin(userDetails)) {
            checkIsStoreOwner(userDetails, store);
        }
    }

    public boolean checkIsAdmin(UserDetailsImpl userDetails) {

        User user = userService.findById(userDetails.getUser().getId());

        return user.getRole().equals(UserRole.MANAGER) || user.getRole().equals(UserRole.MASTER);
    }

    public void checkIsStoreOwner(UserDetailsImpl userDetails, Store store) {

        User user = userService.findById(userDetails.getUser().getId());

        if (!store.getUser().equals(user)) {
            throw new StoreException(ExceptionMessage.STORE_UNAUTHORIZED);
        }
    }
}

package com.fight_world.mono.domain.store.service;

import com.fight_world.mono.domain.store.dto.request.StoreRegisterRequestDto;
import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.repository.StoreRepository;
import com.fight_world.mono.domain.store_category.model.StoreCategory;
import com.fight_world.mono.domain.store_category.service.StoreCategoryService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.value_object.UserEmail;
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
}

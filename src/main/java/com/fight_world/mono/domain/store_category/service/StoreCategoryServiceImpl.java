package com.fight_world.mono.domain.store_category.service;

import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryAddRequestDto;
import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryModifyRequestDto;
import com.fight_world.mono.domain.store_category.dto.response.StoreCategoryResponseDto;
import com.fight_world.mono.domain.store_category.exception.StoreCategoryException;
import com.fight_world.mono.domain.store_category.message.ExceptionMessage;
import com.fight_world.mono.domain.store_category.model.StoreCategory;
import com.fight_world.mono.domain.store_category.repository.StoreCategoryRepository;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCategoryServiceImpl implements StoreCategoryService {

    private final StoreCategoryRepository storeCategoryRepository;
    private final UserService userService;

    @Override
    public StoreCategoryResponseDto addStoreCategory(UserDetailsImpl userDetails,
            StoreCategoryAddRequestDto requestDto) {

        if (storeCategoryRepository.findByCategoryName(requestDto.category_name()).isPresent()) {
            throw new StoreCategoryException(ExceptionMessage.STORE_CATEGORY_ALREADY_EXIST);
        }

        User user = userService.findByUserId(userDetails.getUser().getId());
//        User user = userService.findByUserId(1L);

        StoreCategory savedStoreCategory = storeCategoryRepository.save(
                StoreCategory.of(requestDto));

        return StoreCategoryResponseDto.of(savedStoreCategory);
    }

    @Override
    public StoreCategoryResponseDto modifyCategory(UserDetailsImpl userDetails, String categoryId,
            StoreCategoryModifyRequestDto requestDto) {

        StoreCategory storeCategory = storeCategoryRepository.findById(categoryId).orElseThrow();

        if (!storeCategory.getCategoryName().equals(requestDto.category_name())) {
            checkStoreCategoryNameAlreadyExist(requestDto.category_name());
        }

        storeCategory.updateCategoryName(requestDto.category_name());
        StoreCategory savedStoreCategory = storeCategoryRepository.save(storeCategory);

        return StoreCategoryResponseDto.of(savedStoreCategory);
    }

    @Override
    public StoreCategory findById(String id) {
        return storeCategoryRepository.findById(id)
                .orElseThrow(() -> new StoreCategoryException(ExceptionMessage.STORE_CATEGORY_NOT_FOUND));
    }

    public void checkStoreCategoryNameAlreadyExist(String categoryName) {

        if (storeCategoryRepository.findByCategoryName(categoryName).isPresent()) {
            throw new StoreCategoryException(ExceptionMessage.STORE_CATEGORY_ALREADY_EXIST);
        }
    }
}

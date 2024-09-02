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
import java.util.List;
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

        if (storeCategoryRepository.findByCategoryNameAndDeletedAtIsNull(requestDto.category_name()).isPresent()) {
            throw new StoreCategoryException(ExceptionMessage.STORE_CATEGORY_ALREADY_EXIST);
        }

        User user = userService.findById(userDetails.getUserId());

        StoreCategory savedStoreCategory = storeCategoryRepository.save(StoreCategory.of(requestDto));

        return StoreCategoryResponseDto.from(savedStoreCategory);
    }

    @Override
    public StoreCategoryResponseDto modifyCategory(UserDetailsImpl userDetails, String categoryId,
            StoreCategoryModifyRequestDto requestDto) {

        StoreCategory storeCategory = findById(categoryId);

        if (!storeCategory.getCategoryName().equals(requestDto.category_name())) {
            checkStoreCategoryNameAlreadyExist(requestDto.category_name());
        }

        storeCategory.updateCategoryName(requestDto.category_name());
        StoreCategory savedStoreCategory = storeCategoryRepository.save(storeCategory);

        return StoreCategoryResponseDto.from(savedStoreCategory);
    }

    @Override
    public void deleteCategory(UserDetailsImpl userDetails, String categoryId) {
        StoreCategory storeCategory = findById(categoryId);

        storeCategory.deleteStoreCategory(userDetails.getUserId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreCategoryResponseDto> getStoreCategories() {

        List<StoreCategory> storeCategories = storeCategoryRepository.findAll();

        return storeCategories.stream()
                .map(StoreCategoryResponseDto::from)
                .toList();
    }

    @Override
    public StoreCategory findById(String storeCategoryId) {
        return storeCategoryRepository.findByIdAndDeletedAtIsNull(storeCategoryId)
                .orElseThrow(() -> new StoreCategoryException(ExceptionMessage.STORE_CATEGORY_NOT_FOUND));
    }

    public void checkStoreCategoryNameAlreadyExist(String categoryName) {

        if (storeCategoryRepository.findByCategoryNameAndDeletedAtIsNull(categoryName).isPresent()) {
            throw new StoreCategoryException(ExceptionMessage.STORE_CATEGORY_ALREADY_EXIST);
        }
    }
}

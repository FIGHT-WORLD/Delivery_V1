package com.fight_world.mono.domain.store_category.service;

import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryAddRequestDto;
import com.fight_world.mono.domain.store_category.dto.response.StoreCategoryResponseDto;
import com.fight_world.mono.domain.store_category.exception.StoreCategoryException;
import com.fight_world.mono.domain.store_category.message.ExceptionMessage;
import com.fight_world.mono.domain.store_category.model.StoreCategory;
import com.fight_world.mono.domain.store_category.repository.StoreCategoryRepository;
import com.fight_world.mono.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCategoryServiceImpl implements StoreCategoryService {

    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    public StoreCategoryResponseDto addStoreCategory(UserDetails userDetails,
            StoreCategoryAddRequestDto requestDto) {

        if (storeCategoryRepository.findByCategoryName(requestDto.name()).isPresent()) {
            throw new StoreCategoryException(ExceptionMessage.STORE_CATEGORY_ALREADY_EXIST);
        }

        User user = null; // userService.findById(userDetails.getUser().getId());

        StoreCategory savedStoreCategory = storeCategoryRepository.save(StoreCategory.of(requestDto));

        return StoreCategoryResponseDto.of(savedStoreCategory);
    }

    @Override
    public StoreCategory findById(String id) {
        return storeCategoryRepository.findById(id)
                .orElseThrow(() -> new StoreCategoryException(ExceptionMessage.STORE_CATEGORY_NOT_FOUND));
    }
}

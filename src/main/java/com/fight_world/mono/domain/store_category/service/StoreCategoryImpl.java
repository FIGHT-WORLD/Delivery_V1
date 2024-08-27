package com.fight_world.mono.domain.store_category.service;

import com.fight_world.mono.domain.store_category.repository.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCategoryImpl implements StoreCategory {

    private final StoreCategoryRepository storeCategoryRepository;

}

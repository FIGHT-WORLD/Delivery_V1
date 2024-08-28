package com.fight_world.mono.domain.store.service;

import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store findById(String id) {
        return null;
    }
}

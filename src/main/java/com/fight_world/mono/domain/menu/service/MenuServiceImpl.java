package com.fight_world.mono.domain.menu.service;

import com.fight_world.mono.domain.menu.model.Menu;
import com.fight_world.mono.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public Menu findById(String id) {
        return null;
    }
}

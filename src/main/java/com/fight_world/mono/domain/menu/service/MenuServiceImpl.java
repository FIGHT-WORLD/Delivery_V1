package com.fight_world.mono.domain.menu.service;

import com.fight_world.mono.domain.menu.dto.response.MenuResponseDto;
import com.fight_world.mono.domain.menu.exception.MenuException;
import com.fight_world.mono.domain.menu.message.ExceptionMessage;
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
    public MenuResponseDto getMenu(String menuId) {

        Menu menu = findById(menuId);

        return MenuResponseDto.from(menu);
    }

    @Override
    public Menu findById(String menuId) {

        return menuRepository.findById(menuId)
                .orElseThrow( () -> new MenuException(ExceptionMessage.MENU_NOT_FOUND));
    }
}

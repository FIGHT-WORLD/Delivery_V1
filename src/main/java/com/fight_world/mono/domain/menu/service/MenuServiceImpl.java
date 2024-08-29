package com.fight_world.mono.domain.menu.service;

import com.fight_world.mono.domain.menu.dto.request.AddMenuRequestDto;
import com.fight_world.mono.domain.menu.dto.response.AddMenuResponseDto;
import com.fight_world.mono.domain.menu.dto.response.MenuResponseDto;
import com.fight_world.mono.domain.menu.exception.MenuException;
import com.fight_world.mono.domain.menu.message.ExceptionMessage;
import com.fight_world.mono.domain.menu.model.Menu;
import com.fight_world.mono.domain.menu.repository.MenuRepository;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final UserService userService;
    private final StoreService storeService;

    @Override
    public AddMenuResponseDto addMenu(UserDetailsImpl userDetails, AddMenuRequestDto requestDto) {

        Store store = storeService.findById(requestDto.store_id());
        checkIsStoreOwner(userDetails, store);

        Menu savedMenu = menuRepository.save(Menu.of(requestDto, store));

        return AddMenuResponseDto.from(savedMenu);
    }

    @Override
    public MenuResponseDto getMenu(String menuId) {

        Menu menu = findById(menuId);

        return MenuResponseDto.from(menu);
    }

    @Override
    public Menu findById(String menuId) {

        return menuRepository.findById(menuId)
                .orElseThrow(() -> new MenuException(ExceptionMessage.MENU_NOT_FOUND));
    }

    public void checkIsStoreOwner(UserDetailsImpl userDetails, Store store) {

        User user = userService.findByUserId(userDetails.getUser().getId());

        if (!store.getUser().equals(user)) {
            throw new MenuException(ExceptionMessage.MENU_UNAUTHORIZED);
        }
    }
}

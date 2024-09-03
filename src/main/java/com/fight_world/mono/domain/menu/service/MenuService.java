package com.fight_world.mono.domain.menu.service;

import com.fight_world.mono.domain.menu.dto.request.AddMenuRequestDto;
import com.fight_world.mono.domain.menu.dto.request.ChangeMenuStatusRequestDto;
import com.fight_world.mono.domain.menu.dto.request.ModifyMenuRequestDto;
import com.fight_world.mono.domain.menu.dto.response.AddMenuResponseDto;
import com.fight_world.mono.domain.menu.dto.response.MenuResponseDto;
import com.fight_world.mono.domain.menu.dto.response.ModifyMenuResponseDto;
import com.fight_world.mono.domain.menu.model.Menu;
import com.fight_world.mono.global.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    // 메뉴 등록
    AddMenuResponseDto addMenu(UserDetailsImpl userDetails, AddMenuRequestDto requestDto);

    // 메뉴 상세 조회
    MenuResponseDto getMenu(String menuId);

    // 메뉴 목록 조회(가게별로)
    Page<MenuResponseDto> getMenus(String storeId, Pageable pageable);

    // 메뉴 정보 수정
    ModifyMenuResponseDto modifyMenu(UserDetailsImpl userDetails, String menuId, ModifyMenuRequestDto requestDto);

    // 메뉴 상태 변경
    void changeMenuStatus(UserDetailsImpl userDetails, String menuId, ChangeMenuStatusRequestDto requestDto);

    // 메뉴 삭제
    void deleteMenu(UserDetailsImpl userDetails, String menuId);

    // 메뉴 검색

    // menuId로 메뉴 조회
    Menu findById(String menuId);
}

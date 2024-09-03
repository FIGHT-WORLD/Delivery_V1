package com.fight_world.mono.domain.menu.controller;

import static com.fight_world.mono.domain.menu.message.SuccessMessage.SUCCESS_ADD_MENU;
import static com.fight_world.mono.domain.menu.message.SuccessMessage.SUCCESS_CHANGE_MENU_STATUS;
import static com.fight_world.mono.domain.menu.message.SuccessMessage.SUCCESS_DELETE_MENU;
import static com.fight_world.mono.domain.menu.message.SuccessMessage.SUCCESS_GET_ONE_MENU;
import static com.fight_world.mono.domain.menu.message.SuccessMessage.SUCCESS_GET_STORE_MENUS;
import static com.fight_world.mono.domain.menu.message.SuccessMessage.SUCCESS_MODIFY_MENU;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.menu.dto.request.AddMenuRequestDto;
import com.fight_world.mono.domain.menu.dto.request.ChangeMenuStatusRequestDto;
import com.fight_world.mono.domain.menu.dto.request.ModifyMenuRequestDto;
import com.fight_world.mono.domain.menu.service.MenuService;
import com.fight_world.mono.global.aop.page.PageSizeLimit;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuController {

    private final MenuService menuService;

    /**
     * 메뉴 단건 조회
     */
    @GetMapping("/{menuId}")
    public ResponseEntity<? extends CommonResponse> getMenu(
            @PathVariable(name = "menuId") String menuId) {

        return ResponseEntity.status(SUCCESS_GET_ONE_MENU.getHttpStatus())
                .body(success(SUCCESS_GET_ONE_MENU.getMessage(), menuService.getMenu(menuId)));
    }

    /**
     * 메뉴 목록 조회(가게의)
     */
    @GetMapping("")
    @PageSizeLimit
    public ResponseEntity<? extends CommonResponse> getMenus(
            @RequestParam(value = "storeId") String storeId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(SUCCESS_GET_STORE_MENUS.getHttpStatus())
                             .body(success(SUCCESS_GET_STORE_MENUS.getMessage(),
                                    menuService.getMenus(storeId, pageable)));
    }

    /**
     * 메뉴 등록
     */
    @PreAuthorize("hasAnyRole('ROLE_OWNER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @PostMapping("")
    public ResponseEntity<? extends CommonResponse> addMenu(
            @Valid @RequestBody AddMenuRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(SUCCESS_ADD_MENU.getHttpStatus())
                             .body(success(SUCCESS_ADD_MENU.getMessage(),
                                    menuService.addMenu(userDetails, requestDto)));
    }

    /**
     * 메뉴 수정
     */
    @PreAuthorize("hasAnyRole('ROLE_OWNER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @PutMapping("/{menuId}")
    public ResponseEntity<? extends CommonResponse> modifyMenu(
            @PathVariable(name = "menuId") String menuId,
            @RequestBody ModifyMenuRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(SUCCESS_MODIFY_MENU.getHttpStatus())
                             .body(success(SUCCESS_MODIFY_MENU.getMessage(),
                                    menuService.modifyMenu(userDetails, menuId, requestDto)));

    }

    /**
     * 메뉴 가능여부 상태 변경
     */
    @PreAuthorize("hasAnyRole('ROLE_OWNER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @PatchMapping("/{menuId}/status")
    public ResponseEntity<? extends CommonResponse> changeMenuStatus(
            @PathVariable(name = "menuId") String menuId,
            @RequestBody ChangeMenuStatusRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        menuService.changeMenuStatus(userDetails, menuId, requestDto);

        return ResponseEntity.status(SUCCESS_CHANGE_MENU_STATUS.getHttpStatus())
                             .body(success(SUCCESS_CHANGE_MENU_STATUS.getMessage()));

    }

    /**
     * 메뉴 삭제
     */
    @PreAuthorize("hasAnyRole('ROLE_OWNER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @DeleteMapping("/{menuId}")
    public ResponseEntity<? extends CommonResponse> deleteMenu(
            @PathVariable(name = "menuId") String menuId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        menuService.deleteMenu(userDetails, menuId);

        return ResponseEntity.status(SUCCESS_DELETE_MENU.getHttpStatus())
                             .body(success(SUCCESS_DELETE_MENU.getMessage()));
    }
}

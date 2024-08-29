package com.fight_world.mono.domain.menu.controller;

import static com.fight_world.mono.domain.store.message.SuccessMessage.SUCCESS_GET_STORE_LIST;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.menu.dto.request.AddMenuRequestDto;
import com.fight_world.mono.domain.menu.service.MenuService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{menuId}")
    public ResponseEntity<? extends CommonResponse> getMenu(
            @PathVariable(name = "menuId") String menuId) {

        return ResponseEntity.status(SUCCESS_GET_STORE_LIST.getHttpStatus())
                .body(success(SUCCESS_GET_STORE_LIST.getMessage(), menuService.getMenu(menuId)));
    }

    @PostMapping("")
    public ResponseEntity<? extends CommonResponse> addMenu(
            @Valid @RequestBody AddMenuRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(SUCCESS_GET_STORE_LIST.getHttpStatus())
                .body(success(SUCCESS_GET_STORE_LIST.getMessage(),
                        menuService.addMenu(userDetails, requestDto)));
    }
}

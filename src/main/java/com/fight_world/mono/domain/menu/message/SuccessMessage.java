package com.fight_world.mono.domain.menu.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_ADD_MENU(HttpStatus.CREATED, "메뉴 등록이 완료되었습니다."),
    SUCCESS_MODIFY_MENU(HttpStatus.OK, "메뉴 수정이 완료되었습니다."),
    SUCCESS_DELETE_MENU(HttpStatus.OK, "메뉴 삭제가 완료되었습니다."),
    SUCCESS_GET_ONE_MENU(HttpStatus.OK, "메뉴 조회가 완료되었습니다."),
    SUCCESS_GET_STORE_MENUS(HttpStatus.OK, "가게별 메뉴 목록 조회가 완료되었습니다."),
    SUCCESS_SEARCH_MENU(HttpStatus.OK, "메뉴 검색이 완료되었습니다."),
    SUCCESS_CHANGE_MENU_STATUS(HttpStatus.OK, "메뉴 주문 가능 여부 상태 변경이 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

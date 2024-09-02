package com.fight_world.mono.domain.user.controller;

import static com.fight_world.mono.domain.user.message.SuccessMessage.DELETE_SUCCESS_USER;
import static com.fight_world.mono.domain.user.message.SuccessMessage.SEARCH_SUCCESS_USER;
import static com.fight_world.mono.domain.user.message.SuccessMessage.SELECT_SUCCESS_USER;
import static com.fight_world.mono.domain.user.message.SuccessMessage.SELECT_SUCCESS_USER_LIST;
import static com.fight_world.mono.domain.user.message.SuccessMessage.SIGNUP_SUCCESS_USER;
import static com.fight_world.mono.domain.user.message.SuccessMessage.UPDATE_SUCCESS_USER;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.user.dto.request.UpdateUserRequestDto;
import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.dto.response.DeleteUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.GetUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.GetUserResponseListDto;
import com.fight_world.mono.domain.user.dto.response.SearchUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.SignUpUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.UpdateUserResponseDto;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.domain.user.service.UserServiceImpl;
import com.fight_world.mono.global.aop.page.PageSizeLimit;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /*
    유저 회원가입
     */
    @PostMapping("/sign-up")
    public ResponseEntity<? extends CommonResponse> signUpUser(
            @RequestBody UserSignUpDto requestDto) {

        SignUpUserResponseDto responseDto = userService.signUpUser(requestDto);

        return ResponseEntity
                .status(SIGNUP_SUCCESS_USER.getHttpStatus())
                .body(success(SIGNUP_SUCCESS_USER.getMessage(), responseDto));
    }

    /*
    유저 상세 조회
     */
    @GetMapping("/{userId}")
    public ResponseEntity<? extends CommonResponse> getUser(
            @PathVariable("userId") Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        GetUserResponseDto responseDto = userService.getUser(id, userDetails);

        return ResponseEntity
                .status(SELECT_SUCCESS_USER.getHttpStatus())
                .body(success(SELECT_SUCCESS_USER.getMessage(), responseDto));
    }

    /*
    유저 전체 조회 (admin만)
     */
    @GetMapping
    public ResponseEntity<? extends CommonResponse> getUserList(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

        Page<GetUserResponseListDto> responseDtos = userService.getUserList(userDetails, pageable);

        return ResponseEntity
                .status(SELECT_SUCCESS_USER_LIST.getHttpStatus())
                .body(success(SELECT_SUCCESS_USER_LIST.getMessage(), responseDtos));
    }

    /*
    유저 수정
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<? extends CommonResponse> updateUser(
            @PathVariable("userId") Long id,
            @RequestBody UpdateUserRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        UpdateUserResponseDto responseDto = userService.updateUser(requestDto, id, userDetails);

        return ResponseEntity
                .status(UPDATE_SUCCESS_USER.getHttpStatus())
                .body(success(UPDATE_SUCCESS_USER.getMessage(), responseDto));
    }

    /*
    유저 삭제
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<? extends CommonResponse> deleteUser(
            @PathVariable("userId") Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        DeleteUserResponseDto responseDto = userService.deleteUser(id, userDetails);

        return ResponseEntity
                .status(DELETE_SUCCESS_USER.getHttpStatus())
                .body(success(DELETE_SUCCESS_USER.getMessage(), responseDto));
    }

    /*
    유저 검색 (페이징)
     */
    @GetMapping("/search")
    @PageSizeLimit
    public ResponseEntity<? extends CommonResponse> searchUsers(
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            @RequestParam(value = "query") String query) {

        Page<SearchUserResponseDto> responseDtos = userService.searchUsers(pageable, query);

        return ResponseEntity.status(SEARCH_SUCCESS_USER.getHttpStatus())
                .body(success(SEARCH_SUCCESS_USER.getMessage(), responseDtos));
    }
}

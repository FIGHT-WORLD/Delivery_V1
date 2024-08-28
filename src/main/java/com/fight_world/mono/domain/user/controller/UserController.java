package com.fight_world.mono.domain.user.controller;

import static com.fight_world.mono.domain.user.message.ExceptionMessage.USER_EMAIL_VALID;
import static com.fight_world.mono.domain.user.message.SuccessMessage.DELETE_SUCCESS_USER;
import static com.fight_world.mono.domain.user.message.SuccessMessage.SELECT_SUCCESS_USER;
import static com.fight_world.mono.domain.user.message.SuccessMessage.SIGNUP_SUCCESS_USER;
import static com.fight_world.mono.domain.user.message.SuccessMessage.UPDATE_SUCCESS_USER;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.user.dto.request.UpdateUserRequestDto;
import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.dto.response.DeleteUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.GetUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.SignUpUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.UpdateUserResponseDto;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.domain.user.service.UserServiceImpl;
import com.fight_world.mono.global.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/ex")
    public ResponseEntity<? extends CommonResponse> ex() {

        return ResponseEntity.status(USER_EMAIL_VALID.getHttpStatus())
                .body(success(USER_EMAIL_VALID.getMessage()));
    }

    /*
    유저 회원가입
     */
    @PostMapping("/sign-up")
    public ResponseEntity<? extends CommonResponse> signUpUser(
            @RequestBody UserSignUpDto requestDto
    ) {

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
            @PathVariable("userId") Long id
    ) {

        GetUserResponseDto responseDto = userService.getUser(id);

        return ResponseEntity
                .status(SELECT_SUCCESS_USER.getHttpStatus())
                .body(success(SELECT_SUCCESS_USER.getMessage(), responseDto));
    }

    /*
    유저 수정
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<? extends CommonResponse> patchUser(
            @PathVariable("userId") Long id,
            @RequestBody UpdateUserRequestDto requestDto
    ) {

        UpdateUserResponseDto responseDto = userService.updateUser(requestDto, id);

        return ResponseEntity
                .status(UPDATE_SUCCESS_USER.getHttpStatus())
                .body(success(UPDATE_SUCCESS_USER.getMessage(), responseDto));
    }

    /*
    유저 삭제
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<? extends CommonResponse> deleteUser(
            @PathVariable("userId") Long id
    ) {

        DeleteUserResponseDto responseDto = userService.deleteUser(id);

        return ResponseEntity
                .status(DELETE_SUCCESS_USER.getHttpStatus())
                .body(success(DELETE_SUCCESS_USER.getMessage(), responseDto));
    }
}

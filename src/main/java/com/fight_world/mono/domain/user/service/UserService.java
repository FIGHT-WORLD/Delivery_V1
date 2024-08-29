package com.fight_world.mono.domain.user.service;

import com.fight_world.mono.domain.auth.dto.LoginRequestDto;
import com.fight_world.mono.domain.user.dto.request.UpdateUserRequestDto;
import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.dto.response.DeleteUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.GetUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.SignUpUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.UpdateUserResponseDto;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.value_object.UserEmail;

public interface UserService {

    // 회원가입
    SignUpUserResponseDto signUpUser(UserSignUpDto req);

    // 유저조회
    GetUserResponseDto getUser(Long id);

    // 유저 수정
    UpdateUserResponseDto updateUser(UpdateUserRequestDto req, Long id);

    // 유저 삭제
    DeleteUserResponseDto deleteUser(Long deletedId, Long deletedBy);

    // 다른 서비스용 유저 조회
    User findById(Long id);

    // login 나중에 분리 필요
    void login(LoginRequestDto requestDto);

    void checkDuplicatedUsername(String username);

    void checkPreviousUserPassword(String rawPassword, String encodedPassword);

    void checkDuplicatedEmail(UserEmail userEmail);

    void checkDuplicatedNickname(String nickname);
}

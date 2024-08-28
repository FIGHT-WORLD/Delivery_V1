package com.fight_world.mono.domain.user.service;

import com.fight_world.mono.domain.user.dto.request.UpdateUserRequestDto;
import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.dto.response.DeleteUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.GetUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.SignUpUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.UpdateUserResponseDto;
import com.fight_world.mono.domain.user.model.User;

public interface UserService {

    // 회원가입
    SignUpUserResponseDto signUpUser(UserSignUpDto req);

    // 유저조회
    GetUserResponseDto getUser(Long id);

    // 유저 수정
    UpdateUserResponseDto updateUser(UpdateUserRequestDto req, Long id);

    // 유저 삭제
    DeleteUserResponseDto deleteUser(Long id);

    // 다른 서비스용 유저 조회
    User findByUserId(Long id);
}

package com.fight_world.mono.domain.user.service;

import com.fight_world.mono.domain.user.dto.request.*;
import com.fight_world.mono.domain.user.dto.response.*;

public interface UserService {

    // 회원가입
    SignUpUserResponseDto signUpUser(UserSignUpDto req);

    // 유저조회
    GetUserResponseDto getUser(Long id);

    // 유저 수정
    UpdateUserResponseDto updateUser(UpdateUserRequestDto req, Long id);

    // 유저 삭제
    DeleteUserResponseDto deleteUser(Long id);
}

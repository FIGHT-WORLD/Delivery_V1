package com.fight_world.mono.domain.user.service;

import com.fight_world.mono.domain.user.model.User;

public interface UserService {

//    // 회원가입
//    SignUpUserResponseDto signUpUser(SignUpUserRequestDto req);
//
//    // 유저조회
//    GetUserResponseDto getUser(Long id);
//
//    // 유저 수정
//    PatchUserPasswordResponseDto changeUserPassword(PatchUserPasswordRequestDto req);
//    PatchUserEmailResponseDto changeUserEmail(PatchUserEmailRequestDto req);
//    PatchUserNicknameResponseDto changeUserNickname(PatchUserNicknameRequestDto req);
//
//    // 유저 삭제
//    DeleteUserResponseDto deleteUser(DeleteUserRequestDto req);

    User findById(Long id);
}

package com.fight_world.mono.domain.user.service;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    @Override
//    public SignUpUserResponseDto signUpUser(SignUpUserRequestDto req) {
//        return null;
//    }
//
//    @Override
//    public GetUserResponseDto getUser(Long id) {
//        return null;
//    }
//
//    @Override
//    public PatchUserPasswordResponseDto changeUserPassword(PatchUserPasswordRequestDto req) {
//        return null;
//    }
//
//    @Override
//    public PatchUserEmailResponseDto changeUserEmail(PatchUserEmailRequestDto req) {
//        return null;
//    }
//
//    @Override
//    public PatchUserNicknameResponseDto changeUserNickname(PatchUserNicknameRequestDto req) {
//        return null;
//    }
//
//    @Override
//    public DeleteUserResponseDto deleteUser(DeleteUserRequestDto req) {
//        return null;
//    }


    @Override
    public User findById(Long id) {
        return null;
    }
}

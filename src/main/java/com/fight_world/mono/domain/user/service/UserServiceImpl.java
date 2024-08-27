package com.fight_world.mono.domain.user.service;

import com.fight_world.mono.domain.user.dto.request.UpdateUserRequestDto;
import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.dto.response.DeleteUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.GetUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.SignUpUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.UpdateUserResponseDto;
import com.fight_world.mono.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public SignUpUserResponseDto signUpUser(UserSignUpDto req) {

        return null;
    }

    @Override
    public GetUserResponseDto getUser(Long id) {
        return null;
    }

    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestDto req, Long id) {
        return null;
    }

    @Override
    public DeleteUserResponseDto deleteUser(Long id) {
        return null;
    }
}

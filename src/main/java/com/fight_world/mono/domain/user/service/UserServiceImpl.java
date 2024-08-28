package com.fight_world.mono.domain.user.service;

import com.fight_world.mono.domain.auth.dto.LoginRequestDto;
import com.fight_world.mono.domain.user.dto.request.UpdateUserRequestDto;
import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.dto.response.DeleteUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.GetUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.SignUpUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.UpdateUserResponseDto;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
todo: IllegalArgumentException을 따로 예외 클래스를 정의하기
*/
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    todo :: password encoding하기
    todo :: 관리자와 매니저는 회원가입할때 secretkey를 받도록 하기
     */
    @Transactional
    @Override
    public SignUpUserResponseDto signUpUser(UserSignUpDto req) {

        User user = User.of(req, passwordEncoder.encode(req.password()));

        //회원중복 확인
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException();
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException();
        }
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new IllegalArgumentException();
        }

        userRepository.save(user);

        return SignUpUserResponseDto.from(user);
    }

    /*
    todo :: 조회시 deletedAt에 값이 있으면 조회 안되게 하기
     */

    @Override
    public GetUserResponseDto getUser(Long id) {

        User user = findByUserId(id);

        return GetUserResponseDto.from(user);
    }

    /*
    todo :: 엡데이트 시도시 이전 값과 변경하려는 값이 같을 경우 에러 만들어주기
    todo :: password encoding 하기
     */
    @Transactional
    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestDto req, Long id) {

        User updatedUser = findByUserId(id);

        // 각각의 필드에 올바른 값이 들어왔을 때만 업데이트
        if (!req.password().isEmpty() && !req.password().isBlank()) {
            updatedUser.updatePassword(req);
        }
        if (!req.email().isEmpty() || !req.email().isBlank()) {
            updatedUser.updateEmail(req);
        }
        if (!req.nickname().isEmpty() || !req.nickname().isBlank()) {
            updatedUser.updateNickname(req);
        }

        userRepository.save(updatedUser);

        return UpdateUserResponseDto.from(updatedUser);
    }

    @Override
    public DeleteUserResponseDto deleteUser(Long id) {

        User deletedUser = findByUserId(id);
        deletedUser.deleteUser();
        userRepository.save(deletedUser);

        return DeleteUserResponseDto.from(deletedUser);
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void login(LoginRequestDto requestDto) {

        User user = userRepository.findByUsername(requestDto.username())
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 id")
                );

        if (!passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않음");
        }
    }
}

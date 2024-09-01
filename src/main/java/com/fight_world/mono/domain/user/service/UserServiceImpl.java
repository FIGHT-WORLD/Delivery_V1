package com.fight_world.mono.domain.user.service;

import com.fight_world.mono.domain.auth.dto.LoginRequestDto;
import com.fight_world.mono.domain.review.model.Review;
import com.fight_world.mono.domain.user.dto.request.UpdateUserRequestDto;
import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.dto.response.DeleteUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.GetUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.SignUpUserResponseDto;
import com.fight_world.mono.domain.user.dto.response.UpdateUserResponseDto;
import com.fight_world.mono.domain.user.exception.UserException;
import com.fight_world.mono.domain.user.message.ExceptionMessage;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.UserRole;
import com.fight_world.mono.domain.user.model.value_object.UserEmail;
import com.fight_world.mono.domain.user.repository.UserRepository;
import com.fight_world.mono.global.security.UserDetailsImpl;
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
    todo :: 관리자와 매니저는 회원가입할때 secretkey를 받도록 하기
     */
    @Transactional
    @Override
    public SignUpUserResponseDto signUpUser(UserSignUpDto req) {

        // 회원 정보 중복 확인
        checkDuplicatedUsername(req.username());
        checkDuplicatedEmail(new UserEmail(req.email()));
        checkDuplicatedNickname(req.nickname());
        User user = User.of(req, passwordEncoder.encode(req.password()));
        userRepository.save(user);

        return SignUpUserResponseDto.from(user);
    }

    /*
    todo :: 조회시 deletedAt에 값이 있으면 조회 안되게 하기
     */
    @Override
    public GetUserResponseDto getUser(Long id) {

        User user = findById(id);
        isDeletedUser(user);

        return GetUserResponseDto.from(user);
    }

    /*
    todo :: 업데이트 시도시 이전 값과 변경하려는 값이 같을 경우 에러 만들어주기
    todo :: password encoding 하기
     */
    @Transactional
    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestDto req, Long userId, UserDetailsImpl userDetails) {

        verifyCreatorOfAdmin(userDetails.getUser(), userId);
        User updatedUser = findById(userId);
        isDeletedUser(updatedUser);
        checkPreviousUserPassword(req.password(), updatedUser.getPassword());
        checkDuplicatedEmail(new UserEmail(req.email()));
        checkDuplicatedNickname(req.nickname());
        updatedUser.updateEmail(req.email());
        updatedUser.updatePassword(passwordEncoder.encode(req.password()));
        updatedUser.updateNickname(req.nickname());
        userRepository.save(updatedUser);

        return UpdateUserResponseDto.from(updatedUser);
    }

    @Override
    @Transactional
    public DeleteUserResponseDto deleteUser(Long deletedId, UserDetailsImpl userDetails) throws UserException {

        Long deletedBy = userDetails.getUserId();
        if (!verifyCreatorOfAdmin(userDetails.getUser(), deletedId)) {
            throw new UserException(ExceptionMessage.DELETE_INVALID_AUTHORIZATION);
        }
        User deletedUser = findById(deletedId);
        isDeletedUser(deletedUser);
        deletedUser.deleteUser(deletedBy);
        userRepository.save(deletedUser);

        return DeleteUserResponseDto.from(deletedUser);
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserException(ExceptionMessage.SELECT_NOT_FOUND_USER));
    }

    @Override
    public void login(LoginRequestDto requestDto) {

        User user = userRepository.findByUsername(requestDto.username())
                .orElseThrow(() -> new UserException(ExceptionMessage.LOGIN_NOT_FOUND_USER));

        if (!passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            throw new UserException(ExceptionMessage.LOGIN_NOT_MATCH_PASSWORD);
        }
    }

    @Override
    public void checkDuplicatedUsername(String username) {

        if (userRepository.existsByUsername(username)) {
            throw new UserException(ExceptionMessage.SIGNUP_DUPLICATED_USERNAME);
        }
    }

    @Override
    public void checkPreviousUserPassword(String rawPassword, String encodedPassword) {

        if (passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new UserException(ExceptionMessage.UPDATE_DUPLICATED_PASSWROD);
        }
    }

    @Override
    public void checkDuplicatedEmail(UserEmail userEmail) {

        if (userRepository.existsByEmail(userEmail)) {
            throw new UserException(ExceptionMessage.SIGNUP_DUPLICATED_EMAIL);
        }
    }

    @Override
    public void checkDuplicatedNickname(String nickname) {

        if (userRepository.existsByNickname(nickname)) {
            throw new UserException(ExceptionMessage.SIGNUP_DUPLICATED_NICKNAME);
        }
    }

    @Override
    public Boolean verifyCreatorOrAdmin(User user, Review review) {

        if (user.getRole() == UserRole.MANAGER || user.getRole() == UserRole.MASTER) {
            return true;
        }

        return review.getUser().getId().equals(user.getId());
    }

    private static void isDeletedUser(User user) {

        if(user.getDeletedAt() != null) {
            throw new UserException(ExceptionMessage.SELECT_NOT_FOUND_USER);
        }
    }

    private static boolean verifyCreatorOfAdmin(User user, Long userId) {

        if (user.getRole() == UserRole.MANAGER || user.getRole() == UserRole.MASTER) {
            return true;
        }

        return user.getId().equals(userId);
    }
}

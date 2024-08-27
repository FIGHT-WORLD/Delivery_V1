package com.fight_world.mono.domain.user.model;

import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.model.value_object.UserEmail;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Embedded
    private UserEmail email;

    private UserRole role;

    private String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    public User(String username, String password, UserEmail email, UserRole role, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
    }

    /*
    권한과 함께 유저 생성
     */
    public static User of(UserSignUpDto userSignUpDto, String encodedPassword) {

        return User.builder()
                   .username(userSignUpDto.getUsername())
                   .password(encodedPassword)
                   .email(new UserEmail(userSignUpDto.getEmail()))
                   .role(UserRole.valueOf(userSignUpDto.getRole()))
                   .nickname(userSignUpDto.getNickname())
                   .build();
    }
}

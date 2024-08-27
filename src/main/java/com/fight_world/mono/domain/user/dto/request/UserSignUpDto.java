package com.fight_world.mono.domain.user.dto.request;

// TODO Valid 추가
public record UserSignUpDto(
        String username,

        String password,

        String email,

        String role, // Enum 으로 변환해야 합니당

        String nickname
) {

}

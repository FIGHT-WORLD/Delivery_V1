package com.fight_world.mono.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

// TODO Valid 추가
public record UserSignUpDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String email,
        @NotBlank
        String nickname,
        @NotBlank
        String role
) {

}

package com.fight_world.mono.domain.user.dto.request;

public record UpdateUserRequestDto(

        String password,
        String nickname,
        String email
) {

}

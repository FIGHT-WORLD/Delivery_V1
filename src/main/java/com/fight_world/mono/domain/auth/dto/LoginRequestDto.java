package com.fight_world.mono.domain.auth.dto;

public record LoginRequestDto(
        String username,
        String password
) {

}

package com.fight_world.mono.domain.auth;

import com.fight_world.mono.domain.auth.dto.LoginRequestDto;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.domain.user.service.UserServiceImpl;
import com.fight_world.mono.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
userSerivce와 AuthService를 분리해야할까?
 */
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    AuthController(UserServiceImpl userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /*
    todo :: dto valid 필요
    테스트를 위해 정교하게 작업하지 않음
     */
    @PostMapping("/users/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletResponse httpServletResponse
    ) {

        userService.login(loginRequestDto);
        httpServletResponse.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createAccessToken(loginRequestDto.username()));
        return ResponseEntity.ok().body("로그인 성공");
    }
}

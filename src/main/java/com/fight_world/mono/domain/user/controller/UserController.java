package com.fight_world.mono.domain.user.controller;

import static com.fight_world.mono.domain.user.message.ExceptionMessage.USER_EMAIL_VALID;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.global.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/ex")
    public ResponseEntity<? extends CommonResponse> ex() {

        return ResponseEntity.status(USER_EMAIL_VALID.getHttpStatus())
                             .body(success(USER_EMAIL_VALID.getMessage()));
    }

}

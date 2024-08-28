package com.fight_world.mono.UserTests;

import static org.assertj.core.api.Assertions.assertThat;

import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTests {

    private final UserRepository userRepository;

    UserRepositoryTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();
    }

    @DisplayName("user의 static 메서드를 사용해 저장(패스워드 encode되지 않음)")
    @Test
    void test1() throws Exception {
        //given
        UserSignUpDto req = new UserSignUpDto(
                "user1234",
                "asdf1234",
                "mail1234@mail.com",
                "user",
                "zxcv1234");

        User user = User.of(req, req.password());
        //when
        userRepository.saveAndFlush(user);
        //then
        assertThat(userRepository.findByUsername("user1234").get()).isEqualTo(user);
    }
}

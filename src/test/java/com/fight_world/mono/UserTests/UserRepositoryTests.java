package com.fight_world.mono.UserTests;

import static org.assertj.core.api.Assertions.assertThat;

import com.fight_world.mono.domain.user.dto.request.UserSignUpDto;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();
    }

    @DisplayName("user의 static 메서드를 사용해 저장(패스워드 encode됨)")
    @Test
    void test1() throws Exception {
        //given
        UserSignUpDto req = new UserSignUpDto(
                "user1234",
                "asdf1234",
                "mail1234@mail.com",
                "user",
                "zxcv1234");

        String encodedPassword = passwordEncoder.encode(req.password());
        User user = User.of(req, encodedPassword);

        //when
        userRepository.saveAndFlush(user);

        //then
        User foundUser = userRepository.findByUsername("user1234").get();
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getPassword()).isEqualTo(encodedPassword);
    }
}

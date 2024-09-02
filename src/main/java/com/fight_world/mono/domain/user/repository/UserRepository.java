package com.fight_world.mono.domain.user.repository;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.value_object.UserEmail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(UserEmail email);

    boolean existsByNickname(String nickname);

    List<User> findAllByUserId(Long userId);
}

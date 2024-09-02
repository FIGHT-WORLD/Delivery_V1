package com.fight_world.mono.domain.user.repository;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.value_object.UserEmail;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(UserEmail email);

    boolean existsByNickname(String nickname);

    Page<User> findByUsernameContainingAndDeletedAtIsNull(Pageable pageable, String query);
}

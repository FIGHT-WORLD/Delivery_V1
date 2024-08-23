package com.fight_world.mono.domain.user.repository;

import com.fight_world.mono.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

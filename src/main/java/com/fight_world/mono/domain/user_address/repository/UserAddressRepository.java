package com.fight_world.mono.domain.user_address.repository;

import com.fight_world.mono.domain.user_address.model.UserAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, String> {

    List<UserAddress> findAllByUserId(Long id);
}

package com.fight_world.mono.domain.user_address.repository;

import com.fight_world.mono.domain.user_address.model.UserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, String> {

    Page<UserAddress> findAllByUserIdAndDeletedAtIsNotNull(Long id, Pageable pageable);

    Page<UserAddress> findAllByUserId(Long id, Pageable pageable);

    Page<UserAddress> findByAddressContainingAndDeletedAtIsNull(String query, Pageable pageable);

    Page<UserAddress> findByUserIdAndAddressContainingAndDeletedAtIsNull(Long id, String query,
            Pageable pageable);
}

package com.fight_world.mono.domain.address.repository;

import com.fight_world.mono.domain.address.model.AddressDongeupmyun;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDongeupmyunRepository extends JpaRepository<AddressDongeupmyun, String> {

    Optional<AddressDongeupmyun> findByCodeAndDeletedAtIsNull(String code);
}

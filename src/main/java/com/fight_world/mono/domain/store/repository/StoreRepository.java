package com.fight_world.mono.domain.store.repository;

import com.fight_world.mono.domain.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

    boolean existsByName(String name);
}

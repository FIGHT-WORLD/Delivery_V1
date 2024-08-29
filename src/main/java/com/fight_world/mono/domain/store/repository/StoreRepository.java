package com.fight_world.mono.domain.store.repository;

import com.fight_world.mono.domain.store.model.Store;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

    Optional<Store> findByIdAndDeletedAtIsNull(String id);

    boolean existsByName(String name);

    Page<Store> findByNameContaining(String query, Pageable pageable);

    Page<Store> findAllByStoreCategoryId(String storeCategoryId, Pageable pageable);
}

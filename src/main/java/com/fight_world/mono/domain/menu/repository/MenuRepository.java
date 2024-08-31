package com.fight_world.mono.domain.menu.repository;

import com.fight_world.mono.domain.menu.model.Menu;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {

    Optional<Menu> findByIdAndDeletedAtIsNull(String id);

    Page<Menu> findAllByStoreIdAndDeletedAtIsNull(String storeId, Pageable pageable);
}

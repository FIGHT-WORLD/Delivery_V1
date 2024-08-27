package com.fight_world.mono.domain.menu.repository;

import com.fight_world.mono.domain.menu.model.Menu;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {

}

package com.fight_world.mono.domain.store_category.repository;

import com.fight_world.mono.domain.store_category.model.StoreCategory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory, UUID> {

}

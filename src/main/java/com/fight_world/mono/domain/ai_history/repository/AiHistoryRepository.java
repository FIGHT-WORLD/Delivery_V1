package com.fight_world.mono.domain.ai_history.repository;

import com.fight_world.mono.domain.ai_history.model.AiHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiHistoryRepository extends JpaRepository<AiHistory, String> {

    Page<AiHistory> findAllByStoreId(String storeId, Pageable pageable);
}

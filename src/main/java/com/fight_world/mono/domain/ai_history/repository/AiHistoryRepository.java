package com.fight_world.mono.domain.ai_history.repository;

import com.fight_world.mono.domain.ai_history.model.AiHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiHistoryRepository extends JpaRepository<AiHistory, String> {

}

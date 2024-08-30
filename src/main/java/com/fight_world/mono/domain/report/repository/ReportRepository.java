package com.fight_world.mono.domain.report.repository;

import com.fight_world.mono.domain.report.model.Report;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {

    Optional<Report> findById(String reportId);

    List<Report> findByUserId(Long userId);

}
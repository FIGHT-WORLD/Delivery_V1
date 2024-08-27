package com.fight_world.mono.domain.report.repository;

import com.fight_world.mono.domain.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report<Long>, Long> {

}

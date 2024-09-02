package com.fight_world.mono.domain.report.repository;

import com.fight_world.mono.domain.report.model.Report;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {


    Page<Report> findByTitleContaining(String title, Pageable pageable);

    Page<Report> findByContentContaining(String content, Pageable pageable);

    Page<Report> findByUserIdAndTitleContaining(Long userId, String title, Pageable pageable);

    Page<Report> findByUserIdAndContentContaining(Long userId, String content, Pageable pageable);


    Page<Report> findById(Long userId, Pageable pageable);

    Page<Report> findByUserId(Long userId, Pageable pageable);
}
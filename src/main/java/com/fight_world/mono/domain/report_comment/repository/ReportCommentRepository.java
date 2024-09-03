package com.fight_world.mono.domain.report_comment.repository;

import com.fight_world.mono.domain.report_comment.model.ReportComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportCommentRepository extends JpaRepository<ReportComment, String> {


    List<ReportComment> findByReportId(String reportId);
}

package com.fight_world.mono.domain.report_comment.repository;

import com.fight_world.mono.domain.report_comment.model.ReportComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportCommentRepository extends JpaRepository<ReportComment, String> {
    

}

package com.fight_world.mono.domain.report_comment.dto;

import com.fight_world.mono.domain.report_comment.model.ReportComment;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CreateReportCommentResponseDto(
        String reportId,
        Long userId,
        String commentId,

        String content,

        LocalDateTime createdAt

) {

    public static CreateReportCommentResponseDto from(ReportComment reportComment) {

        return CreateReportCommentResponseDto.builder()
                .reportId(reportComment.getReport().getId())
                .userId(reportComment.getUser().getId())
                .commentId(reportComment.getId())
                .content(reportComment.getContent())
                .createdAt(reportComment.getCreatedAt())
                .build();
    }

}

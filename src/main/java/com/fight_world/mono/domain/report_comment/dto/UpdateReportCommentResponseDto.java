package com.fight_world.mono.domain.report_comment.dto;

import com.fight_world.mono.domain.report_comment.model.ReportComment;
import java.time.LocalDateTime;
import lombok.Builder;


@Builder
public record UpdateReportCommentResponseDto(
        Long userId,
        String commentId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UpdateReportCommentResponseDto from(ReportComment reportComment) {
        return UpdateReportCommentResponseDto.builder()
                .userId(reportComment.getUser().getId())
                .commentId(reportComment.getReport().getId())
                .content(reportComment.getContent())
                .createdAt(reportComment.getCreatedAt())
                .updatedAt(reportComment.getUpdatedAt())
                .build();
    }
}

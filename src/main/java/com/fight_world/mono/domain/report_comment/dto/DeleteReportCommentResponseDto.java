package com.fight_world.mono.domain.report_comment.dto;

import com.fight_world.mono.domain.report_comment.model.ReportComment;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record DeleteReportCommentResponseDto(
        LocalDateTime deleteAt,
        Long deleteBy
) {

    public static DeleteReportCommentResponseDto from(ReportComment reportComment) {
        return DeleteReportCommentResponseDto.builder()
                .deleteAt(reportComment.getDeletedAt())
                .deleteBy(reportComment.getDeletedBy())
                .build();
    }
}

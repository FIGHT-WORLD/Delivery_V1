package com.fight_world.mono.domain.report_comment.dto;

public record CreateReportCommentRequestDto(
        String content,
        String reportId
) {

}

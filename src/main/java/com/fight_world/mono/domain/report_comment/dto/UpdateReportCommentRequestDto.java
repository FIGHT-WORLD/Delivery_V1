package com.fight_world.mono.domain.report_comment.dto;

public record UpdateReportCommentRequestDto(
        Long userId,
        String reportCommentId,
        String content

) {

}

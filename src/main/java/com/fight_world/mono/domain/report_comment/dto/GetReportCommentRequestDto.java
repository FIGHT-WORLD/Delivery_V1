package com.fight_world.mono.domain.report_comment.dto;

public record GetReportCommentRequestDto(
        Long userId,
        String commentId,
        String content
) {

}

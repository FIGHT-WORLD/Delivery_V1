package com.fight_world.mono.domain.report.dto;

public record GetReportRequestDto(
        Long userId,
        String storeId,
        String reportType,
        String title,
        String content
) {

}

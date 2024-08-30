package com.fight_world.mono.domain.report.dto;

import java.time.LocalDateTime;

public record UpdateReportRequestDto(
        Long userId,
        String storeId,
        String reportType,
        String title,
        String content,
        LocalDateTime issuedAt
) {

}

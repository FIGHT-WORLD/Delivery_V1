package com.fight_world.mono.domain.report.dto;

import java.time.LocalDateTime;

public record CreateReportRequestDto(

        String storeId,
        LocalDateTime issueDate,
        String reportType,
        String title,
        String content
) {

}


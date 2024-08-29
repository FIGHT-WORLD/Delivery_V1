package com.fight_world.mono.domain.report.dto;

import com.fight_world.mono.domain.report.model.Report;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record DeleteReportResponseDto(

        LocalDateTime deletedAt
) {

    public static DeleteReportResponseDto from(Report report) {
        return DeleteReportResponseDto.builder()
                .deletedAt(report.getDeletedAt())
                .build();
    }
}

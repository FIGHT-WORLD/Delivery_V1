package com.fight_world.mono.domain.report.dto;

import com.fight_world.mono.domain.report.model.Report;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record UpdateReportResponseDto(
        String reportId,
        Long userId,
        String storeId,
        LocalDateTime issuedAt,
        String reportType,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UpdateReportResponseDto from(Report report) {

        String storeId;

        if (report.getStore() == null) {
            storeId = null;
        } else {
            storeId = report.getStore().getId();
        }

        return UpdateReportResponseDto.builder()
                .userId(report.getUser().getId())
                .reportId(report.getId())
                .storeId(storeId)
                .reportType(report.getReportType())
                .title(report.getTitle())
                .content(report.getContent())
                .issuedAt(report.getIssuedAt())
                .createdAt(report.getCreatedAt())
                .updatedAt(report.getUpdatedAt())
                .build();
    }
}

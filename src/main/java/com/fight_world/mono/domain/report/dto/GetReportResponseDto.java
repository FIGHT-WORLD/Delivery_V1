package com.fight_world.mono.domain.report.dto;

import com.fight_world.mono.domain.report.model.Report;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetReportResponseDto(
        String reportId,
        Long userId,
        String storeId,
        LocalDateTime issueDate,
        String reportType,
        Long counselId,
        String title,
        String content,
        LocalDateTime createdAt
) {

    public static GetReportResponseDto from(Report report) {

        String storeId = report.getStore() != null ? report.getStore().getId() : null;

        return GetReportResponseDto.builder()
                .reportId(report.getId())
                .userId(report.getUser().getId())
                .storeId(storeId)
                .issueDate(report.getIssuedAt())
                .reportType(report.getReportType())
                .counselId(report.getCounselorId())
                .title(report.getTitle())
                .content(report.getContent())
                .createdAt(report.getCreatedAt())
                .build();
    }
}
package com.fight_world.mono.domain.report.dto;

import com.fight_world.mono.domain.report.model.Report;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetReportResponseDto(

        String reportId,
        Long userId,
        String storeId,
        String reportType,
        String title,
        String content,
        LocalDateTime issuedAt,
        LocalDateTime createdAt
) {

    public static GetReportResponseDto from(Report report) {

        return GetReportResponseDto.builder()
                .userId(report.getUser().getId())
                .reportId(report.getId())
                .storeId(report.getStore().getId())
                .reportType(report.getReportType())
                .title(report.getTitle())
                .content(report.getContent())
                .issuedAt(report.getIssueDate())
                .createdAt(report.getCreatedAt())
                .build();
    }
}

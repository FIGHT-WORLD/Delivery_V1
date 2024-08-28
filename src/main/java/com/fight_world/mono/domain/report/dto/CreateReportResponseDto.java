package com.fight_world.mono.domain.report.dto;

import com.fight_world.mono.domain.report.model.Report;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CreateReportResponseDto(
        String reportId,
        Long userId,
        String storeId,
        String reportType,
        String title,
        String content,
        LocalDateTime issuedAt,
        LocalDateTime createdAt
) {
    public static CreateReportResponseDto from(Report report) {

        return CreateReportResponseDto.builder()
                .reportId(report.getId())
                .userId(report.getUser().getId())
                .storeId(report.getStore().getId())
                .issuedAt(report.getIssueDate())
                .reportType(report.getReportType())
                .title(report.getTitle())
                .content(report.getContent())
                .createdAt(report.getCreatedAt())
                .build();
    }

}

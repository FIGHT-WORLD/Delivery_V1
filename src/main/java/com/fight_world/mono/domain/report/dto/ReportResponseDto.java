package com.fight_world.mono.domain.report.dto;

import com.fight_world.mono.domain.report.model.Report;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ReportResponseDto {

    private Long reportId;
    private Long userId;
    private Long storeId;
    private String issueDate;
    private String reportType;
    private String title;
    private String content;
    private String createdAt;

    public static ReportResponseDto of(Report report) {
        return ReportResponseDto.builder()
                .reportId(report.getId())
                .userId(report.getUser().getId())
                .storeId(report.getStore() != null ? Long.valueOf(report.getStore().getId()) : null)
                .issueDate(report.getIssueDate().toString())
                .reportType(report.getReportType())
                .title(report.getTitle())
                .content(report.getContent())
                .createdAt(String.valueOf(report.getCreatedAt()))
                .build();
    }

}

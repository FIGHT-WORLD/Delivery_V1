package com.fight_world.mono.domain.report.dto;

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

}

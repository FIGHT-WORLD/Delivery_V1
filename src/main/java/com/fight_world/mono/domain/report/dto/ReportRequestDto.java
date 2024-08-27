package com.fight_world.mono.domain.report.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequestDto {

    private Long userId;
    private Long storeId;
    private LocalDateTime issueDate;
    private String reportType;
    private String title;
    private String content;
}

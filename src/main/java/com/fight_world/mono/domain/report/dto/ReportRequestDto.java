package com.fight_world.mono.domain.report.dto;

import com.fight_world.mono.domain.report.model.Report;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.user.model.User;
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

    public Report toEntity(User user, Store store) {
        Report report = Report.createReport(
                user,
                store,
                title,
                content,
                issueDate,
                reportType
        );

        return report;
    }
}


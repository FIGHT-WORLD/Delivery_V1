package com.fight_world.mono.domain.report_comment.model;

import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.report.model.Report;
import com.fight_world.mono.domain.report_comment.dto.CreateReportCommentRequestDto;
import com.fight_world.mono.domain.report_comment.dto.UpdateReportCommentRequestDto;
import com.fight_world.mono.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_report_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportComment extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder(access = AccessLevel.PRIVATE)
    public ReportComment(String id, User user, Report report, String content) {
        this.id = id;
        this.user = user;
        this.report = report;
        this.content = content;
    }

    public static ReportComment of(CreateReportCommentRequestDto createReportCommentRequestDto,
            User user, Report report) {
        return ReportComment.builder()
                .user(user)
                .report(report)
                .content(createReportCommentRequestDto.content())
                .build();
    }

    public void updateComment(UpdateReportCommentRequestDto requestDto) {
        if (requestDto.content() != null) {
            this.content = requestDto.content();
        }
    }

    public void deleteAt(Long userId) {
        super.setDeleted(userId);
    }


}

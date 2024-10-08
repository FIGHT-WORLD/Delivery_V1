package com.fight_world.mono.domain.report.model;

import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.report.dto.CreateReportRequestDto;
import com.fight_world.mono.domain.report.dto.UpdateReportRequestDto;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.global.security.UserDetailsImpl;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_report")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "issue_date", nullable = false)
    private LocalDateTime issuedAt;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "counselor_id")
    private Long counselorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder(access = AccessLevel.PRIVATE)
    public Report(String title, String content, LocalDateTime issuedAt, String reportType,
            User user, Store store) {
        this.title = title;
        this.content = content;
        this.issuedAt = issuedAt;
        this.reportType = reportType;
        this.user = user;
        this.store = store;
    }

    public static Report of(CreateReportRequestDto createReportRequestDto,
            User user, Store store) {

        return Report.builder()
                .user(user)
                .title(createReportRequestDto.title())
                .content(createReportRequestDto.content())
                .issuedAt(createReportRequestDto.issueDate())
                .reportType(createReportRequestDto.reportType())
                .store(store == null ? null : store)
                .build();
    }


    public void update(UpdateReportRequestDto requestDto) {
        this.title = requestDto.title() != null ? requestDto.title() : this.title;
        this.content = requestDto.content() != null ? requestDto.content() : this.content;
    }


    public void deletedAt(Long userId) {
        super.setDeleted(userId);
    }
}

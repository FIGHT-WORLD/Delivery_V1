package com.fight_world.mono.domain.report.service;

import com.fight_world.mono.domain.report.dto.ReportRequestDto;
import com.fight_world.mono.domain.report.dto.ReportResponseDto;
import com.fight_world.mono.domain.report.model.Report;
import com.fight_world.mono.domain.report.repository.ReportRepository;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.repository.UserRepository;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    private final StoreRepository storeRepository;

    public ReportService(ReportRepository reportRepository, UserRepository userRepository, StoreRepository storeRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }


    // 신고 작성
    @Transactional
    public ReportResponseDto createReport(ReportRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        Report report = Report.createReport(
                user,
                store,
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getIssueDate(),
                requestDto.getReportType()
        );

        reportRepository.save(report);

        return ReportResponseDto.builder()
                .reportId(report.getId())
                .userId(report.getUser().getId())
                .storeId(Long.valueOf(report.getStore().getId()))
                .issueDate(report.getIssueDate().toString())
                .reportType(report.getReportType())
                .title(report.getTitle())
                .content(report.getContent())
                .createdAt(report.getCreatedAt()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }

}

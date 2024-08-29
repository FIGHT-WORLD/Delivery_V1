package com.fight_world.mono.domain.report.service;

import com.fight_world.mono.domain.report.dto.CreateReportRequestDto;
import com.fight_world.mono.domain.report.dto.CreateReportResponseDto;
import com.fight_world.mono.domain.report.dto.DeleteReportResponseDto;
import com.fight_world.mono.domain.report.dto.GetReportResponseDto;
import com.fight_world.mono.domain.report.dto.UpdateReportRequestDto;
import com.fight_world.mono.domain.report.dto.UpdateReportResponseDto;
import com.fight_world.mono.domain.report.model.Report;
import com.fight_world.mono.domain.report.repository.ReportRepository;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;
    private final StoreService storeService;

    public ReportService(ReportRepository reportRepository, UserService userService,
            StoreService storeService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
        this.storeService = storeService;
    }

    // 신고 작성
    @Transactional
    public CreateReportResponseDto createReport(CreateReportRequestDto requestDto, Long userId) {

        User user = userService.findByUserId(userId);
        Store store = storeService.findById(requestDto.storeId());
        Report report = Report.of(requestDto, user, store);

        reportRepository.save(report);

        return CreateReportResponseDto.from(report);
    }


    // 신고 조회
    public GetReportResponseDto getReport(String reportId) {

        Report report = findById(reportId);

        return GetReportResponseDto.from(report);
    }


    // 신고 수정
    public UpdateReportResponseDto updateReport(UpdateReportRequestDto requestDto,
            String reportId) {

        Report report = findById(reportId);
        report.updateTitle(requestDto);
        report.updateContent(requestDto);
        reportRepository.save(report);

        return UpdateReportResponseDto.from(report);
    }


    // 신고 삭제
    public DeleteReportResponseDto deleteReport(String reportId, Long userId) {

        Report report = findById(reportId);
        report.deletedAt(userId);
        reportRepository.save(report);

        return DeleteReportResponseDto.from(report);
    }

    private Report findById(String reportId) {

        return reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("조회한 reportId가 없습니다."));
    }
}

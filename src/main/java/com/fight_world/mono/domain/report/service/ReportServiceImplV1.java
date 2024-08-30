package com.fight_world.mono.domain.report.service;

import com.fight_world.mono.domain.report.dto.CreateReportRequestDto;
import com.fight_world.mono.domain.report.dto.CreateReportResponseDto;
import com.fight_world.mono.domain.report.dto.DeleteReportResponseDto;
import com.fight_world.mono.domain.report.dto.GetReportResponseDto;
import com.fight_world.mono.domain.report.dto.UpdateReportRequestDto;
import com.fight_world.mono.domain.report.dto.UpdateReportResponseDto;
import com.fight_world.mono.domain.report.exception.ReportException;
import com.fight_world.mono.domain.report.message.ExceptionMessage;
import com.fight_world.mono.domain.report.model.Report;
import com.fight_world.mono.domain.report.repository.ReportRepository;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportServiceImplV1 implements ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;
    private final StoreService storeService;

    // 신고 작성
    @Transactional
    public CreateReportResponseDto createReport(CreateReportRequestDto requestDto,
            UserDetailsImpl userDetails) {

        User user = userService.findById(userDetails.getUser().getId());

        Store store = null;
        if (requestDto.storeId() != null) {
            store = storeService.findById(requestDto.storeId());
        }

        Report report = Report.of(requestDto, user, store);

        reportRepository.save(report);

        return CreateReportResponseDto.from(report);
    }


    // 신고 상세 조회
    @Transactional(readOnly = true)
    public GetReportResponseDto getReport(String reportId, UserDetailsImpl userDetails) {

        Report report = findById(reportId);

        if (!report.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new ReportException(ExceptionMessage.YOUR_NOT_REPORTER);
        }

        return GetReportResponseDto.from(report);
    }


    // 신고 목록 조회(고객)
    @Transactional(readOnly = true)
    public List<GetReportResponseDto> getReportListByUser(UserDetailsImpl userDetails) {

        List<Report> reports = reportRepository.findByUserId(userDetails.getUser().getId());

        return reports.stream().map(GetReportResponseDto::from).collect(Collectors.toList());
    }

    // 신고 목록 조회 (관리자)
    @Transactional(readOnly = true)
    public List<GetReportResponseDto> getAllReportsForAdmin() {

        List<Report> reports = reportRepository.findAll();

        return reports.stream().map(GetReportResponseDto::from).collect(Collectors.toList());
    }


    // 신고 수정
    @Transactional
    public UpdateReportResponseDto updateReport(UpdateReportRequestDto requestDto,
            String reportId, UserDetailsImpl userDetails) {

        Report report = findById(reportId);
        report.updateTitle(requestDto);
        report.updateContent(requestDto);

        return UpdateReportResponseDto.from(report);
    }


    // 신고 삭제
    @Transactional
    public DeleteReportResponseDto deleteReport(String reportId, UserDetailsImpl userDetails) {

        Report report = findById(reportId);
        report.deletedAt(userDetails.getUser().getId());

        return DeleteReportResponseDto.from(report);
    }

    @Transactional
    public Report findById(String reportId) {

        return reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("조회한 reportId가 없습니다."));
    }

}

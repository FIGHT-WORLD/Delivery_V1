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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        User user = userService.findById(userDetails.getUserId());

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

        if (!report.getUser().getId().equals(userDetails.getUserId())) {
            throw new ReportException(ExceptionMessage.YOUR_NOT_REPORTER);
        }

        return GetReportResponseDto.from(report);
    }


    // 신고 목록 조회(고객)
    @Transactional(readOnly = true)
    public Page<GetReportResponseDto> getReportListByUser(UserDetailsImpl userDetails,
            Pageable pageable) {
        Long userId = userDetails.getUser().getId();
        Page<Report> reportsPage = reportRepository.findByUserId(userId, pageable);
        return reportsPage.map(GetReportResponseDto::from);
    }

    // 신고 목록 조회 (관리자)
    @Transactional(readOnly = true)
    public Page<GetReportResponseDto> getAllReportsForAdmin(UserDetailsImpl userDetails,
            Pageable pageable) {
        if (!userDetails.getUser().getRole().name().equals("MASTER")) {
            throw new ReportException(ExceptionMessage.REPORT_ADMIN);
        }

        Page<Report> reports = reportRepository.findAll(pageable);

        return reports.map(GetReportResponseDto::from);
    }


    // 신고 목록 검색(관리자)
    @Transactional(readOnly = true)
    public Page<GetReportResponseDto> searchReports(String keyword, Pageable pageable,
            UserDetailsImpl userDetails) {

        if (!userDetails.getUser().getRole().name().equals("MASTER")) {
            throw new ReportException(ExceptionMessage.YOUR_NOT_REPORTER);
        }

        // 키워드가 없는 경우
        if (keyword == null || keyword.isEmpty()) {
            throw new ReportException(ExceptionMessage.KEYWORD_EMPTY);
        }

        Page<Report> reportsPage = Page.empty();

        if (keyword.startsWith("title:")) {
            // 제목 검색
            String titleKeyword = keyword.replaceFirst("title:", "").trim();
            reportsPage = reportRepository.findByTitleContaining(titleKeyword, pageable);
        } else if (keyword.startsWith("content:")) {
            // 내용 검색
            String contentKeyword = keyword.replaceFirst("content:", "").trim();
            reportsPage = reportRepository.findByContentContaining(contentKeyword, pageable);
        }

        if (reportsPage.isEmpty()) {
            throw new ReportException(ExceptionMessage.KEYWORD_NOT_PROVIDED);
        }

        return reportsPage.map(GetReportResponseDto::from);
    }


    // 신고 목록 검색(고객)
    @Transactional(readOnly = true)
    public Page<GetReportResponseDto> searchReportsByUser(String keyword, Pageable pageable,
            UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        // 키워드가 없는 경우
        if (keyword == null || keyword.isEmpty()) {
            throw new ReportException(ExceptionMessage.KEYWORD_EMPTY);
        }

        Page<Report> reportsPage = Page.empty();

        if (keyword.startsWith("title:")) {
            // 제목 검색
            String titleKeyword = keyword.replaceFirst("title:", "").trim();
            reportsPage = reportRepository.findByUserIdAndTitleContaining(userId, titleKeyword,
                    pageable);
        } else if (keyword.startsWith("content:")) {
            // 내용 검색
            String contentKeyword = keyword.replaceFirst("content:", "").trim();
            reportsPage = reportRepository.findByUserIdAndContentContaining(userId, contentKeyword,
                    pageable);
        }

        // 검색 결과가 없는 경우 예외 처리
        if (reportsPage.isEmpty()) {
            throw new ReportException(ExceptionMessage.KEYWORD_NOT_PROVIDED);
        }

        return reportsPage.map(GetReportResponseDto::from);
    }

    // 신고 수정
    @Transactional
    public UpdateReportResponseDto updateReport(UpdateReportRequestDto requestDto,
            String reportId, UserDetailsImpl userDetails) {

        Report report = findById(reportId);
        report.update(requestDto);

        return UpdateReportResponseDto.from(report);
    }


    // 신고 삭제
    @Transactional
    public DeleteReportResponseDto deleteReport(String reportId, UserDetailsImpl userDetails) {

        Report report = findById(reportId);
        report.deletedAt(userDetails.getUserId());

        return DeleteReportResponseDto.from(report);
    }

    @Transactional
    public Report findById(String reportId) {

        return reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("조회한 reportId가 없습니다."));
    }


}

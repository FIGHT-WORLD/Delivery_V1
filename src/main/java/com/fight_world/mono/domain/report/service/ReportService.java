package com.fight_world.mono.domain.report.service;

import com.fight_world.mono.domain.report.dto.CreateReportRequestDto;
import com.fight_world.mono.domain.report.dto.CreateReportResponseDto;
import com.fight_world.mono.domain.report.dto.DeleteReportResponseDto;
import com.fight_world.mono.domain.report.dto.GetReportResponseDto;
import com.fight_world.mono.domain.report.dto.UpdateReportRequestDto;
import com.fight_world.mono.domain.report.dto.UpdateReportResponseDto;
import com.fight_world.mono.domain.report.model.Report;
import com.fight_world.mono.global.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {

    // 신고 작성
    CreateReportResponseDto createReport(CreateReportRequestDto requestDto,
            UserDetailsImpl userDetails);

    // 신고 상세 조회
    GetReportResponseDto getReport(String reportId, UserDetailsImpl userDetails);

    // 신고 목록 조회(고객)
    Page<GetReportResponseDto> getReportListByUser(UserDetailsImpl userDetails, Pageable pageable);


    // 신고 목록 조회(관리자)
//    List<GetReportResponseDto> getAllReportsForAdmin();
    Page<GetReportResponseDto> getAllReportsForAdmin(UserDetailsImpl userDetails,
            Pageable pageable);

    // 신고 수정
    UpdateReportResponseDto updateReport(UpdateReportRequestDto requestDto,
            String reportId, UserDetailsImpl userDetails);

    // 신고 삭제
    DeleteReportResponseDto deleteReport(String reportId, UserDetailsImpl userDetails);

    Report findById(String reportId);

    // 신고 검색
    Page<GetReportResponseDto> searchReports(String keyword, Pageable pageable,
            UserDetailsImpl userDetails);

    Page<GetReportResponseDto> searchReportsByUser(String keyword, Pageable pageable,
            UserDetailsImpl userDetails);


}

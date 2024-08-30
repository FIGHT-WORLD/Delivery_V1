package com.fight_world.mono.domain.report.controller;

import static com.fight_world.mono.domain.report.message.SuccessMessage.CREATED_REPORT;
import static com.fight_world.mono.domain.report.message.SuccessMessage.DELETE_REPORT;
import static com.fight_world.mono.domain.report.message.SuccessMessage.GET_REPORT;
import static com.fight_world.mono.domain.report.message.SuccessMessage.GET_REPORT_DETAIL;
import static com.fight_world.mono.domain.report.message.SuccessMessage.UPDATE_REPORT;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.report.dto.CreateReportRequestDto;
import com.fight_world.mono.domain.report.dto.DeleteReportResponseDto;
import com.fight_world.mono.domain.report.dto.GetReportResponseDto;
import com.fight_world.mono.domain.report.dto.UpdateReportRequestDto;
import com.fight_world.mono.domain.report.dto.UpdateReportResponseDto;
import com.fight_world.mono.domain.report.service.ReportService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    // 신고 작성
    @PostMapping
    public ResponseEntity<? extends CommonResponse> createReport(
            @RequestBody CreateReportRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(CREATED_REPORT.getHttpStatus())
                .body(success(CREATED_REPORT.getMessage(),
                        reportService.createReport(requestDto, userDetails)));
    }


    // 신고 상세 조회
    @GetMapping("/{reportId}")
    public ResponseEntity<? extends CommonResponse> getReport(
            @PathVariable("reportId") String id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        GetReportResponseDto responseDto = reportService.getReport(id, userDetails);

        return ResponseEntity.status(GET_REPORT_DETAIL.getHttpStatus())
                .body(success(GET_REPORT_DETAIL.getMessage(), responseDto));
    }

    // 신고 목록 조회(고객)
    @GetMapping
    public ResponseEntity<? extends CommonResponse> getReportsByUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<GetReportResponseDto> responseDto = reportService.getReportListByUser(userDetails);

        return ResponseEntity.status(GET_REPORT.getHttpStatus())
                .body(success(GET_REPORT.getMessage(), responseDto));
    }

    // 신고 목록 조회(관리자)
    @GetMapping("/admin")
    public ResponseEntity<? extends CommonResponse> getAllReportsForAdmin(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<GetReportResponseDto> responseDto = reportService.getAllReportsForAdmin();

        return ResponseEntity.status(GET_REPORT.getHttpStatus())
                .body(success(GET_REPORT.getMessage(), responseDto));
    }

    // 신고 수정
    @PutMapping("/{reportId}")
    public ResponseEntity<? extends CommonResponse> updateReport(
            @PathVariable("reportId") String id,
            @RequestBody UpdateReportRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        UpdateReportResponseDto responseDto = reportService.updateReport(requestDto, id,
                userDetails);

        return ResponseEntity.status(UPDATE_REPORT.getHttpStatus())
                .body(success(UPDATE_REPORT.getMessage(), responseDto));
    }

    // 신고 삭제
    @DeleteMapping("/{reportId}")
    public ResponseEntity<? extends CommonResponse> deleteReport(
            @PathVariable("reportId") String reportId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        DeleteReportResponseDto responseDto = reportService.deleteReport(reportId,
                userDetails);

        return ResponseEntity.status(DELETE_REPORT.getHttpStatus())
                .body(success(DELETE_REPORT.getMessage(), responseDto));
    }


}

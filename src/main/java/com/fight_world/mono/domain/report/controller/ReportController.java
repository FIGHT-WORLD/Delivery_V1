package com.fight_world.mono.domain.report.controller;

import static com.fight_world.mono.domain.report.message.SuccessMessage.CREATED_REPORT;
import static com.fight_world.mono.domain.report.message.SuccessMessage.DELETE_REPORT;
import static com.fight_world.mono.domain.report.message.SuccessMessage.GET_REPORT;
import static com.fight_world.mono.domain.report.message.SuccessMessage.GET_REPORT_DETAIL;
import static com.fight_world.mono.domain.report.message.SuccessMessage.SEARCH_REPORT;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(CREATED_REPORT.getHttpStatus())
                .body(success(CREATED_REPORT.getMessage(),
                        reportService.createReport(requestDto, userDetails)));
    }


    // 신고 상세 조회
    @GetMapping("/{reportId}")
    public ResponseEntity<? extends CommonResponse> getReport(@PathVariable("reportId") String id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        GetReportResponseDto responseDto = reportService.getReport(id, userDetails);

        return ResponseEntity.status(GET_REPORT_DETAIL.getHttpStatus())
                .body(success(GET_REPORT_DETAIL.getMessage(), responseDto));
    }

    @GetMapping("/admin/{reportId}")
    public ResponseEntity<? extends CommonResponse> getReportAdmin(@PathVariable("reportId") String id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        GetReportResponseDto responseDto = reportService.getReportAdmin(id, userDetails);

        return ResponseEntity.status(GET_REPORT_DETAIL.getHttpStatus())
                .body(success(GET_REPORT_DETAIL.getMessage(), responseDto));
    }

    // 신고 목록 조회(고객)
    @GetMapping
    public ResponseEntity<? extends CommonResponse> getReportsByUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        if (size != 10 && size != 30 && size != 50) {
            size = 10;
        }

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<GetReportResponseDto> responseDto = reportService.getReportListByUser(userDetails,
                pageable);

        return ResponseEntity.status(GET_REPORT.getHttpStatus())
                .body(success(GET_REPORT.getMessage(), responseDto));
    }

    // 신고 목록 조회(관리자)
    @GetMapping("/admin")
    public ResponseEntity<? extends CommonResponse> getAllReportsForAdmin(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        if (size != 10 && size != 30 && size != 50) {
            size = 10;
        }

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<GetReportResponseDto> responseDto = reportService.getAllReportsForAdmin(userDetails,
                pageable);

        return ResponseEntity.status(GET_REPORT.getHttpStatus())
                .body(success(GET_REPORT.getMessage(), responseDto));

    }

    // 신고 목록 검색 - 관리자
    @GetMapping("/admin/search")
    public ResponseEntity<? extends CommonResponse> searchReports(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("keyword") String keyword, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        if (size != 10 && size != 30 && size != 50) {
            size = 10;
        }

        if (!sortBy.equals("createdAt") && !sortBy.equals("updatedAt")) {
            sortBy = "createdAt";
        }

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<GetReportResponseDto> responseDto = reportService.searchReports(keyword, pageRequest,
                userDetails);

        return ResponseEntity.status(SEARCH_REPORT.getHttpStatus())
                .body(success(SEARCH_REPORT.getMessage(), responseDto));

    }

    // 신고 목록 검색 - 고객
    @GetMapping("/search")
    public ResponseEntity<? extends CommonResponse> searchReportsByUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("keyword") String keyword, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        if (size != 10 && size != 30 && size != 50) {
            size = 10;
        }

        if (!sortBy.equals("createdAt") && !sortBy.equals("updatedAt")) {
            sortBy = "createdAt";
        }

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<GetReportResponseDto> responseDto = reportService.searchReportsByUser(keyword,
                pageRequest,
                userDetails);

        return ResponseEntity.status(SEARCH_REPORT.getHttpStatus())
                .body(success(SEARCH_REPORT.getMessage(), responseDto));

    }


    // 신고 수정
    @PutMapping("/{reportId}")
    public ResponseEntity<? extends CommonResponse> updateReport(
            @PathVariable("reportId") String id, @RequestBody UpdateReportRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        UpdateReportResponseDto responseDto = reportService.updateReport(requestDto, id,
                userDetails);

        return ResponseEntity.status(UPDATE_REPORT.getHttpStatus())
                .body(success(UPDATE_REPORT.getMessage(), responseDto));
    }

    // 신고 삭제
    @DeleteMapping("/{reportId}")
    public ResponseEntity<? extends CommonResponse> deleteReport(
            @PathVariable("reportId") String reportId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        DeleteReportResponseDto responseDto = reportService.deleteReport(reportId, userDetails);

        return ResponseEntity.status(DELETE_REPORT.getHttpStatus())
                .body(success(DELETE_REPORT.getMessage(), responseDto));
    }


}

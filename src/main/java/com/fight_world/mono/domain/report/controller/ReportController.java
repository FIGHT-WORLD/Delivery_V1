package com.fight_world.mono.domain.report.controller;

import com.fight_world.mono.domain.report.dto.CreateReportRequestDto;
import com.fight_world.mono.domain.report.dto.CreateReportResponseDto;
import com.fight_world.mono.domain.report.dto.DeleteReportResponseDto;
import com.fight_world.mono.domain.report.dto.GetReportResponseDto;
import com.fight_world.mono.domain.report.dto.UpdateReportRequestDto;
import com.fight_world.mono.domain.report.dto.UpdateReportResponseDto;
import com.fight_world.mono.domain.report.service.ReportService;
import com.fight_world.mono.global.jwt.JwtUtil;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final JwtUtil jwtUtil;


    // 신고 작성
    @PostMapping
    public ResponseEntity<CreateReportResponseDto> createReport(
            @RequestBody CreateReportRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        CreateReportResponseDto responseDto = reportService.createReport(requestDto,
                userDetails.getUser().getId());

        return ResponseEntity.ok(responseDto);
    }


    // 상세 조회
    /*
        todo :: 권한추가
     */
    @GetMapping("/{reportId}")
    public ResponseEntity<GetReportResponseDto> getReport(
            @PathVariable("reportId") String id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        GetReportResponseDto responseDto = reportService.getReport(id);

        return ResponseEntity.ok(responseDto);
    }

    // 신고 수정
    @PutMapping("/{reportId}")
    public ResponseEntity<UpdateReportResponseDto> updateReport(
            @PathVariable("reportId") String id,
            @RequestBody UpdateReportRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        UpdateReportResponseDto responseDto = reportService.updateReport(requestDto, id);

        return ResponseEntity.ok(responseDto);
    }

    // 신고 삭제
    @DeleteMapping("/{reportId}")
    public ResponseEntity<DeleteReportResponseDto> deleteReport(
            @PathVariable("reportId") String reportId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        DeleteReportResponseDto responseDto = reportService.deleteReport(reportId,
                userDetails.getUser().getId());

        return ResponseEntity.ok(responseDto);
    }
}

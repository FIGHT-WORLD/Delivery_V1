package com.fight_world.mono.domain.report.controller;

import com.fight_world.mono.domain.report.dto.CreateReportRequestDto;
import com.fight_world.mono.domain.report.dto.CreateReportResponseDto;
import com.fight_world.mono.domain.report.dto.GetReportResponseDto;
import com.fight_world.mono.domain.report.service.ReportService;
import com.fight_world.mono.global.jwt.JwtUtil;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CreateReportRequestDto requestDto) {

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
}

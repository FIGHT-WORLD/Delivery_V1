//package com.fight_world.mono.domain.report.controller;
//
//import com.fight_world.mono.domain.report.dto.ReportRequestDto;
//import com.fight_world.mono.domain.report.dto.ReportResponseDto;
//import com.fight_world.mono.domain.report.model.Report;
//import com.fight_world.mono.domain.report.service.ReportService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/reports")
//public class ReportController {
//
//    private final ReportService reportService;
//
//    public ReportController(ReportService reportService) {
//        this.reportService = reportService;
//    }
//
//    // 신고 작성
//    @PostMapping
//    public ResponseEntity<ReportResponseDto> createReport(
//            @RequestBody ReportRequestDto requestDto) {
//        ReportResponseDto responseDto = reportService.createReport(requestDto);
//        return ResponseEntity.ok(responseDto);
//    }
//}

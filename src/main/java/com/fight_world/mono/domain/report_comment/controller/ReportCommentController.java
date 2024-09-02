package com.fight_world.mono.domain.report_comment.controller;

import static com.fight_world.mono.domain.report_comment.message.SuccessMessage.CREATE_COMMENT;
import static com.fight_world.mono.domain.report_comment.message.SuccessMessage.DELETE_COMMENT;
import static com.fight_world.mono.domain.report_comment.message.SuccessMessage.GET_COMMENT;
import static com.fight_world.mono.domain.report_comment.message.SuccessMessage.UPDATE_COMMENT;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.report_comment.dto.CreateReportCommentRequestDto;
import com.fight_world.mono.domain.report_comment.dto.DeleteReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.dto.GetReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.dto.UpdateReportCommentRequestDto;
import com.fight_world.mono.domain.report_comment.dto.UpdateReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.service.ReportCommentService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
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
@RequestMapping("/api/v1/report/admin")
public class ReportCommentController {

    private final ReportCommentService reportCommentService;

    // 신고 답변 작성
    @PostMapping("/{reportId}/feedback")
    public ResponseEntity<? extends CommonResponse> createComment(
            @PathVariable("reportId") String reportId,
            @RequestBody CreateReportCommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.status(CREATE_COMMENT.getHttpStatus())
                .body(success(CREATE_COMMENT.getMessage(),
                        reportCommentService.createReportComment(reportId, requestDto,
                                userDetails)));
    }

    // 신고 답변 수정
    @PutMapping("{reportCommentId}")
    public ResponseEntity<? extends CommonResponse> updateComment(
            @PathVariable("reportCommentId") String commentId,
            @RequestBody UpdateReportCommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UpdateReportCommentResponseDto responseDto = reportCommentService.updateComment(requestDto,
                commentId, userDetails);

        return ResponseEntity.status(UPDATE_COMMENT.getHttpStatus())
                .body(success(UPDATE_COMMENT.getMessage(), responseDto));
    }

    // 신고 답변 조회
    @GetMapping()
    public ResponseEntity<? extends CommonResponse> getAllReportComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<GetReportCommentResponseDto> responseDto = reportCommentService.getAllReportComments(
                userDetails);

        return ResponseEntity.status(GET_COMMENT.getHttpStatus())
                .body(success(GET_COMMENT.getMessage(), responseDto));
    }

    // 신고 개별 답변 조회
    @GetMapping("/{reportId}/feedback")
    public ResponseEntity<? extends CommonResponse> getCommentsByReportId(
            @PathVariable("reportId") String reportId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<GetReportCommentResponseDto> responseDto = reportCommentService.getCommentsByReportId(
                reportId, userDetails);

        return ResponseEntity.status(GET_COMMENT.getHttpStatus())
                .body(success(GET_COMMENT.getMessage(), responseDto));
    }

    // 신고 답변 삭제
    @DeleteMapping("{reportCommentId}")
    public ResponseEntity<? extends CommonResponse> deleteReportComment(
            @PathVariable("reportCommentId") String commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        DeleteReportCommentResponseDto responseDto = reportCommentService.deleteReportComment(
                commentId,
                userDetails);

        return ResponseEntity.status(DELETE_COMMENT.getHttpStatus())
                .body(success(DELETE_COMMENT.getMessage(), responseDto));
    }


}

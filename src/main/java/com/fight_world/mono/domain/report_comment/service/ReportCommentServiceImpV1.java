package com.fight_world.mono.domain.report_comment.service;

import com.fight_world.mono.domain.report.model.Report;
import com.fight_world.mono.domain.report.service.ReportService;
import com.fight_world.mono.domain.report_comment.dto.CreateReportCommentRequestDto;
import com.fight_world.mono.domain.report_comment.dto.CreateReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.dto.DeleteReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.dto.GetReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.dto.UpdateReportCommentRequestDto;
import com.fight_world.mono.domain.report_comment.dto.UpdateReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.model.ReportComment;
import com.fight_world.mono.domain.report_comment.repository.ReportCommentRepository;
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
public class ReportCommentServiceImpV1 implements ReportCommentService {

    private final ReportCommentRepository reportCommentRepository;
    private final UserService userService;
    private final ReportService reportService;


    // 신고 답변 작성
    @Transactional
    public CreateReportCommentResponseDto createReportComment(String reportId,
            CreateReportCommentRequestDto requestDto, UserDetailsImpl userDetails) {

        User user = userService.findById(userDetails.getUser().getId());

        Report report = reportService.findById(reportId);
        ReportComment reportComment = ReportComment.of(requestDto, user, report);

        reportCommentRepository.save(reportComment);

        return CreateReportCommentResponseDto.from(reportComment);
    }


    // 신고 답변 수정
    @Transactional
    public UpdateReportCommentResponseDto updateComment(UpdateReportCommentRequestDto requestDto,
            String commentId, UserDetailsImpl userDetails) {

        ReportComment reportComment = findById(commentId);
        reportComment.updateComment(requestDto);
        reportComment.updateComment(requestDto);

        return UpdateReportCommentResponseDto.from(reportComment);
    }

    // 신고 답변 조회
    @Transactional
    public List<GetReportCommentResponseDto> getAllReportComments() {
        List<ReportComment> reportComments = reportCommentRepository.findAll();

        return reportComments.stream().map(GetReportCommentResponseDto::from)
                .collect(Collectors.toList());
    }


    // 신고 답변 삭제
    @Transactional
    public DeleteReportCommentResponseDto deleteReportComment(String commentId,
            UserDetailsImpl userDetails) {

        ReportComment reportComment = findById(commentId);
        reportComment.deleteAt(userDetails.getUser().getId());

        return DeleteReportCommentResponseDto.from(reportComment);
    }


    @Transactional
    public ReportComment findById(String commentId) {

        return reportCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("조회한 commentId가 없습니다."));
    }
}

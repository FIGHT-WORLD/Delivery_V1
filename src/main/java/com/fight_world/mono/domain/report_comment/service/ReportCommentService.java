package com.fight_world.mono.domain.report_comment.service;

import com.fight_world.mono.domain.report_comment.dto.CreateReportCommentRequestDto;
import com.fight_world.mono.domain.report_comment.dto.CreateReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.dto.DeleteReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.dto.GetReportCommentResponseDto;
import com.fight_world.mono.domain.report_comment.dto.UpdateReportCommentRequestDto;
import com.fight_world.mono.domain.report_comment.dto.UpdateReportCommentResponseDto;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;

public interface ReportCommentService {

    CreateReportCommentResponseDto createReportComment(String reportId,
            CreateReportCommentRequestDto requestDto, UserDetailsImpl userDetails);

    UpdateReportCommentResponseDto updateComment(UpdateReportCommentRequestDto requestDto,
            String reportCommentId, UserDetailsImpl userDetails);

    List<GetReportCommentResponseDto> getAllReportComments(UserDetailsImpl userDetails);

    DeleteReportCommentResponseDto deleteReportComment(String commentId,
            UserDetailsImpl userDetails);

    List<GetReportCommentResponseDto> getCommentsByReportId(String reportId, UserDetailsImpl userDetails);
}

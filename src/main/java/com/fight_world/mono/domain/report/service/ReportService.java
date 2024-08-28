package com.fight_world.mono.domain.report.service;

import com.fight_world.mono.domain.report.dto.ReportRequestDto;
import com.fight_world.mono.domain.report.dto.ReportResponseDto;
import com.fight_world.mono.domain.report.model.Report;
import com.fight_world.mono.domain.report.repository.ReportRepository;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.repository.StoreRepository;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.repository.UserRepository;
import com.fight_world.mono.domain.user.service.UserService;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;
    private final StoreService storeService;

    public ReportService(ReportRepository reportRepository, UserService userService,
            StoreService storeService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
        this.storeService = storeService;
    }


    // 신고 작성
//    @Transactional
//    public ReportResponseDto createReport(ReportRequestDto requestDto) {
//
//        GetUserResponseDto user = userService.getUser(requestDto.getUserId());
//
//        Store store = storeService.findById(requestDto.getStoreId());
//
//        Report report = requestDto.toEntity(user, store);
//
//        reportRepository.save(report);
//
//        return ReportResponseDto.of(report);
//    }
}

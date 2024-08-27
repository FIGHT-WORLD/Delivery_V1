package com.fight_world.mono.domain.store_category.controller;

import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store-category")
public class StoreCategoryController {


    @GetMapping("/")
    public ResponseEntity<? extends CommonResponse> getStoreCategories() {
        // logic
//        return ResponseEntity.status("")
//                .body(success(""));
        return null;
    }
}

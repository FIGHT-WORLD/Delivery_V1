package com.fight_world.mono.global.util;

import java.util.HashSet;
import java.util.Set;

public class PageSizeSelector {

    private static final Set<Integer> VALID_SIZES = new HashSet<>();
    private static final int DEFAULT_SIZE = 10;

    static {
        // 유효한 페이지 사이즈를 집합에 추가
        VALID_SIZES.add(10);
        VALID_SIZES.add(20);
        VALID_SIZES.add(30);
    }

    public static int validatePageSize(int pageSize) {

        // 입력 값이 유효한 사이즈 중 하나인지 확인
        if (VALID_SIZES.contains(pageSize)) {
            return pageSize;
        } else {
            // 유효하지 않은 경우 기본값 10으로 설정
            return DEFAULT_SIZE;
        }
    }
}

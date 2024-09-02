package com.fight_world.mono.global.aop.page;

import java.util.Set;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PageSizeAspect {

    private static final Set<Integer> VALID_SIZES = Set.of(10, 30, 50);
    private static final int DEFAULT_SIZE = 10;

    @Around("@annotation(PageSizeLimit)")
    public Object checkPageSize(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Pageable) {
                Pageable pageable = (Pageable) args[i];
                int pageSize = pageable.getPageSize();

                if (!VALID_SIZES.contains(pageSize)) {
                    args[i] = PageRequest.of(pageable.getPageNumber(), DEFAULT_SIZE, pageable.getSort());
                }
            }
        }

        return joinPoint.proceed(args);
    }
}

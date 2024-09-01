package com.fight_world.mono.domain.deliveryArea.repository;

import static com.fight_world.mono.domain.deliveryArea.model.QDeliveryArea.deliveryArea;
import static com.fight_world.mono.domain.store.model.QStore.store;
import static com.fight_world.mono.domain.store_category.model.QStoreCategory.storeCategory;

import com.fight_world.mono.domain.deliveryArea.dto.response.DeliveryAvailableStoresResponseDto;
import com.fight_world.mono.domain.store.dto.response.StoreResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class DeliveryAreaQueryRepositoryImpl implements DeliveryAreaQueryRepository {

    private final JPAQueryFactory queryFactory;

    public DeliveryAreaQueryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<StoreResponseDto> findStoresByDongeupmyunCodeAndCategory(String dongeupmyunCode,
            String categoryName, Pageable pageable) {

        List<DeliveryAvailableStoresResponseDto> results = queryFactory
                .select(
                        Projections.constructor(
                                DeliveryAvailableStoresResponseDto.class,
                                store.id.as("storeId"),
                                store.name.as("name"),
                                store.address.as("address"),
                                store.openAt.as("openAt"),
                                store.closeAt.as("closeAt"),
                                store.phoneNumber.as("phoneNumber"),
                                storeCategory.categoryName.as("storeCategory"),
                                store.status.as("status")
                        ))
                .from(deliveryArea)
                .join(deliveryArea.store, store)
                .join(store.storeCategory, storeCategory)
                .where(
                        store.deletedAt.isNull(),
                        deliveryArea.addressDongeupmyun.code.eq(dongeupmyunCode),
                        categoryName != null ? storeCategory.categoryName.eq(categoryName) : null
                )
                .offset(pageable.getOffset())  // 페이징 오프셋 설정
                .limit(pageable.getPageSize())  // 페이징 사이즈 설정
                .fetch();

        // 전체 결과 개수를 구하는 카운트 쿼리
        int total = results.size();

        List<StoreResponseDto> resultDtos = results.stream().map(StoreResponseDto::from).toList();

        // 페이징된 결과를 Page 객체로 반환
        return new PageImpl<>(resultDtos, pageable, total);
    }
}

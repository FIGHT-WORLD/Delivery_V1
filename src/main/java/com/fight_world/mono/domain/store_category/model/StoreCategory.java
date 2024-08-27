package com.fight_world.mono.domain.store_category.model;

import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.store_category.dto.request.StoreCategoryAddRequestDto;
import com.fight_world.mono.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_store_category")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreCategory extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", unique = true)
    private String categoryName;

    @Builder(access = AccessLevel.PRIVATE)
    public StoreCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public static StoreCategory of(StoreCategoryAddRequestDto requestDto) {

        return StoreCategory.builder()
                .categoryName(requestDto.name())
                .build();
    }
}

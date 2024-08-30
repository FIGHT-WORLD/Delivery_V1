package com.fight_world.mono.domain.ai_history.model;

import com.fight_world.mono.domain.ai_history.dto.request.AiProductDescriptionHistoryCreateRequestDto;
import com.fight_world.mono.domain.menu.model.Menu;
import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.store.model.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_ai_history")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiHistory extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "asking", nullable = false, columnDefinition = "TEXT")
    private String asking;

    @Column(name = "answer", nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Builder(access = AccessLevel.PRIVATE)
    public AiHistory(Store store, Menu menu, String asking, String answer) {
        this.store = store;
        this.menu = menu;
        this.asking = asking;
        this.answer = answer;
    }

    public static AiHistory of(Store store, AiProductDescriptionHistoryCreateRequestDto requestDto,
            Menu menu, String answer) {

        return AiHistory.builder()
                .store(store)
                .menu(menu)
                .asking(requestDto.asking())
                .answer(answer)
                .build();
    }
}

package com.fight_world.mono.domain.address.model;

import com.fight_world.mono.domain.model.TimeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_address_sido")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressSido extends TimeBase {

    @Id
    private String code;

    @Column(nullable = false, unique = true)
    private String name;
}

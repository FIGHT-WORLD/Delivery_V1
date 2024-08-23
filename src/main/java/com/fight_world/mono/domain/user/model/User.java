package com.fight_world.mono.domain.user.model;

import com.fight_world.mono.domain.user.model.value_object.UserEmail;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Embedded
    private UserEmail email;

    private String role;

    private String nickname;


}

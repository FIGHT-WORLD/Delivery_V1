package com.fight_world.mono.domain.user.model.value_object;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@ToString
@Getter
public class UserEmail{

    @Column(name = "email", nullable = false, unique = true)
    String value;

    public UserEmail(String value) {
        if (value == null || value.isBlank() || value.length() < 3) {
            throw new IllegalArgumentException("");
        }
        this.value = value;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        UserEmail userEmail = (UserEmail) o;
        
        return value.equals(userEmail.value);
    }

    @Override
    public int hashCode() {

        return value.hashCode();
    }
}

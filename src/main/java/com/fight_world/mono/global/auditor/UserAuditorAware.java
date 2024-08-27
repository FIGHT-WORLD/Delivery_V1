package com.fight_world.mono.global.auditor;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuditorAware implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.of(((UserDetailsImpl) authentication.getPrincipal()).getUser());
    }
}

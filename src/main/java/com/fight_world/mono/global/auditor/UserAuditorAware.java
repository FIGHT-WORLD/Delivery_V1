package com.fight_world.mono.global.auditor;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // anonymousUser 는 임시방편입니다.
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return Optional.empty();
        }

        return Optional.of(((UserDetailsImpl) authentication.getPrincipal()).getUser().getId());
    }
}

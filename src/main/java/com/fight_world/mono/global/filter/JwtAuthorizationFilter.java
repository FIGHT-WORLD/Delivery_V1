package com.fight_world.mono.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fight_world.mono.global.jwt.JwtUtil;
import com.fight_world.mono.global.security.UserDetailsImpl;
import com.fight_world.mono.global.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final ObjectMapper objectMapper;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsServiceImpl,
            ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.objectMapper = objectMapper;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);

        if (Objects.nonNull(token)) {
            if (jwtUtil.validateToken(token)) {
                Claims info = jwtUtil.getUserInfoFromToken(token);

                // Claim에 있는 username을 담고
                // securitycontext 생성
                String username = info.getSubject();
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                // user -> userDetails에 담고
                UserDetailsImpl userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                // AuthenticationPrincipal에 담고
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                // securitycontext 안에 authenticationprincipal을 담고
                context.setAuthentication(authentication);
                // securitycontextholder에 securitycontext 담기
                SecurityContextHolder.setContext(context);
                // @AuthenticationPrincipal로 userDetailsImpl에 담긴 값들 조회 가능
            } else {
                // 미인증 시
                ResponseEntity<String> MessageDto =
                        ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("유효한 토큰이 아닙니다");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(MessageDto));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

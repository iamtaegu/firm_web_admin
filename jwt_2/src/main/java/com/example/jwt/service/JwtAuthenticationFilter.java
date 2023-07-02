package com.example.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 클라이언트 요청시 JWT 인증을 하기 위해 설치하는 커스텀 필터로 UsernamePasswordAuthenticationToken 이전에 실행
 * JwtAuthenticationFilter를 통과하면 UsernamePasswordAuthenticationToken 이후의 필터는 통과한 것으로 본다는 뜻
 * 다시 말하면, Username+Password를 통한 인증을 JWT를 통해 수행한다는 것
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 1. Request Header에서 JWT 토큰 추출
         * 2. ValidateToken으로 토큰 유효성 검사
         *  > 유요한 토큰이라면
         *  > Authentication 객체를 가지고 와서 SecurityContext 에 저장
         *      - SecurityContext에 저장한다는 거면 분산 환경에서는 어떻게 되는거지?
         */

        String token = resolveToken((HttpServletRequest) req);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(req, resp);
    }

    // Request Header 에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

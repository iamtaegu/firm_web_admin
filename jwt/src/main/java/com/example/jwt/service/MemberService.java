package com.example.jwt.service;

import com.example.jwt.domain.MemberRepository;

import com.example.jwt.domain.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenDTO login(String memberId, String password) {
        /**
         * 1. Login ID/PW를 기반으로 Authentication 객체 생성
         *  > 이때 authentication는 인증 여부를 확인하는 authenticated 값이 false
         * 2. 실제 검증
         *  > 사용자 비밀번호 체크
         *  > authenticate 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드 실행
         *      - CustomUserDetailsService.loadUserByUsername 검증을 위한 유저 객체를 가져오는 부분으로
         *      - 어떤 객체를 검증할 것인지에 대해 직접 구현
         * 3. 인증 정보를 기반으로 JWT 토큰 생성
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDTO tokenDTO = jwtTokenProvider.generateToken(authentication);

        return tokenDTO;
    }

}

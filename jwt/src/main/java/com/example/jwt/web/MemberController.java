package com.example.jwt.web;

import com.example.jwt.domain.MemberLoginRequestDTO;
import com.example.jwt.domain.TokenDTO;
import com.example.jwt.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public TokenDTO login(MemberLoginRequestDTO memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenDTO tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }
}

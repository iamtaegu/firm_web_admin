package com.example.jwt.service;

import com.example.jwt.domain.Member;
import com.example.jwt.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
                // Spring Security는 사용자 검증을 위해 encoding된 password와 그렇지 않은 password를 비교하기 때문에
                // PasswordEncoder를 통해 password 인코딩
                /**
                 * PasswordEncoder를 통해 password 인코딩
                 *  > Spring Security는 사용자 검증을 위해 encoding된 password와 그렇지 않은 password를 비교하기 때문에
                 *  DB자체에 encoding된 password 값을 갖고 있으면 encoding 없어도 됨
                 *  > 사용자가 입력하고 Spring Security에 의해 인코딩된 password랑
                 *  > DB에 인코딩된 password랑 비교하기 때문에
                 */
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }
}

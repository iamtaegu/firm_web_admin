package com.example.jwt.config;

import com.example.jwt.service.JwtAuthenticationFilter;
import com.example.jwt.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 과거에는 Security 설정을 WebSecurityConfigurerAdapter 클래스를 상속받아서 구현했지만 deprecated 됐고,
 * 이제는 빈 등록을 통해 Security 설정함
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable() // rest api이므로 basic auth와 csrf 보안을 사용하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT를 사용하기 때문에 세션을 사용하지 않음
                .and()
                .authorizeRequests()
                .antMatchers("/h2/*").permitAll()
                .antMatchers("/members/login").permitAll()
                .antMatchers("/members/test").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠음
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * JWT를 사용하기 위해서는 기본적으로 password encoder가 필요한데,
     * 여기서는 Bycrypt encoder를 사용
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


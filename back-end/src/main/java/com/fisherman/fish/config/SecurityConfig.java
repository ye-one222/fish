package com.fisherman.fish.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizedHttpRequests) -> authorizedHttpRequests
                        // 모든 경로 권한 허용 (일시)
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .csrf((csrf) -> csrf
                        // csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/**")))
                .headers((headers) -> headers
                        // 프레임 관련 (h2 콘솔 띄우기용)
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                        )));

        return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        // 암호화 인코더 인터페이스 지정
        // - 직접 BCryptPasswordEncoder를 사용해도 되지만,
        //   PasswordEncoder로 지정해서 사용하면 코드 이곳 저곳에 흩어진 암호화 인코더를 한번에 관리할 수 있다 
        return new BCryptPasswordEncoder();  // BCrypt 해시 함수를 사용하는 인코더
    }
}

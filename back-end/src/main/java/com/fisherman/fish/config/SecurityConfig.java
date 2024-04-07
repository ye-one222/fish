package com.fisherman.fish.config;

import com.fisherman.fish.filter.FishFilter;
import com.fisherman.fish.filter.JWTAuthFilter;
import com.fisherman.fish.service.FishService;
import com.fisherman.fish.utility.JWTUtil;
import com.fisherman.fish.filter.LoginFilter;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration configuration;
    private final JWTUtil jwtUtil;
    private final FishService fishService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 필터 url 설정
        LoginFilter loginFilter = new LoginFilter(authenticationManager(configuration), jwtUtil);
        loginFilter.setFilterProcessesUrl("/users/login");
        FishFilter fishFilter = new FishFilter(jwtUtil, fishService);
        fishFilter.setFilterProcessesUrl("/fishes/[0-9]+");
        
        http
                // 허용 url 설정
                .authorizeHttpRequests((authorizedHttpRequests) -> authorizedHttpRequests
                        .requestMatchers("/h2-console/**").permitAll() // h2 콘솔 허용
                        .requestMatchers("/users/login", "/users/signup").permitAll() // 로그인, 회원 가입 경로 허용
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{}/fishes").permitAll()/*.hasAnyRole()*/
                        /*.requestMatchers(HttpMethod.GET,
                                "/fishes", "/users", "/users/{userId}").hasRole("ADMIN")*/ // 나중에 주석 해제
                        .anyRequest().permitAll()
                )
                // csrf는 필요 없다
                .csrf((auth) -> auth.disable())
                // form 로그인 방식 해제
                .formLogin((auth) -> auth.disable())
                // http basic 인증 방식 해제 (찾아봐야 할듯)
                .httpBasic((auth) -> auth.disable())
                .headers((headers) -> headers
                        // 프레임 관련 (h2 콘솔 띄우기용)
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                        )))
                ;
        // 필터 등록
        http
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(fishFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthFilter(jwtUtil), LoginFilter.class)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세선 정책 설정 (rest api에 필요)
                ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        // 암호화 인코더 인터페이스 지정
        // - 직접 BCryptPasswordEncoder를 사용해도 되지만,
        //   PasswordEncoder로 지정해서 사용하면 코드 이곳 저곳에 흩어진 암호화 인코더를 한번에 관리할 수 있다 
        return new BCryptPasswordEncoder();  // BCrypt 해시 함수를 사용하는 인코더
    }
}

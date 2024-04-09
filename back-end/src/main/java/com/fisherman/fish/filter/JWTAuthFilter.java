package com.fisherman.fish.filter;

import com.fisherman.fish.entity.CustomUserDetailsEntity;
import com.fisherman.fish.entity.MemberEntity;
import com.fisherman.fish.utility.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        // 토큰이 빈 경우
        if(authorization == null || !authorization.startsWith("Bearer ")){
            System.out.println("JWTAuthFilter : token null; passing jwt filtering");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1];
        // 토큰 소멸 시간 검증
        if(jwtUtil.isExpired(token)){
            System.out.println("JWTAuthFilter : token expired");
            filterChain.doFilter(request, response);
            return;
        }

        // username, role 획득
        String userId = jwtUtil.getUserId(token);
        String role = jwtUtil.getRole(token);

        // MemberEntity 생성
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(userId);
        memberEntity.setPassword("tempPassword");
        memberEntity.setRole(role);

        // UserDetails에 회원 정보 담음
        CustomUserDetailsEntity userDetails = new CustomUserDetailsEntity(memberEntity);

        // 스프링 시큐리티 인증 토큰 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        System.out.println("JWTAuthFilter : Hello, " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.print("JWTAuthFilter : Your role is ");
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().forEachRemaining(
                (GrantedAuthority auth) -> {
                    System.out.print(auth.getAuthority() + " ");
                }
        );
        System.out.println();

        filterChain.doFilter(request, response);
    }
}

package com.fisherman.fish.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // (로그인) 요청을 가로채서 검증을 진행

        // 요청에서 username, password 추출
        String id = request.getParameter("id");
        String password = request.getParameter("password");


        System.out.println("Attempting Authentication - id : '" + id + "' password : '" + password + "'");

        // 스프링 시큐리티에서 username과 password를 검증하기 위해선 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, password, null);

        // token을 검증을 위해 AuthenticationManager에 전달
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication){
        System.out.println("Attempt successful.");
        User user = (User) authentication.getPrincipal();

        String id = user.getUsername();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        System.out.println("Hello, " + user.getUsername() + " / " + role);

        String token = jwtUtil.createJwt(id, role, 60*60*10L);
        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){
        System.out.println("Attempt failed.");
        response.setStatus(401);
    }
}

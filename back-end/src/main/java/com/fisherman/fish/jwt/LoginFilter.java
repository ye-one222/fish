package com.fisherman.fish.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private long tokenExpiredMs = 60*60*10L;

    public void setTokenExpiredMs(long tokenExpiredMs){
        if(tokenExpiredMs < 0 ) return;
        this.tokenExpiredMs = tokenExpiredMs;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 로그인 요청을 가로채서 검증을 진행

        // 요청에서 id, password 추출
        String id, password;
        System.out.println("content-type : " + request.getHeader("content-type")); // test
        if(request.getHeader("content-type").startsWith("application/json")) {
            try {
                // json으로 요청한 경우 파싱한다
                BufferedReader br = request.getReader();
                String jsonString = br.lines().collect(Collectors.joining(System.lineSeparator()));
                Map<String, String> jsonRequest = new ObjectMapper().readValue(jsonString, Map.class);
                jsonRequest.forEach((key, val) -> System.out.println(key + " : " + val)); // test
                id = jsonRequest.get("id");
                password = jsonRequest.get("password");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            // form으로 요청한 경우 파라미터에서 꺼낸다
            id = request.getParameter("id");
            password = request.getParameter("password");
        }

        System.out.println("Attempting Authentication - id : '" + id + "' password : '" + password + "'"); // test

        // 스프링 시큐리티에서 username과 password를 검증하기 위해서 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, password, null);

        // token 검증을 위해 AuthenticationManager에 전달
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication){
        System.out.println("Attempt successful.");
        User user = (User) authentication.getPrincipal();

        String id = user.getUsername();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        System.out.println("Hello, " + user.getUsername() + " / " + role);

        String token = jwtUtil.createJwt(id, role, tokenExpiredMs);
        //response.addHeader("Authorization", "Bearer " + token);
        try {
            // body로 jwt 발급
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter writer = response.getWriter();
            Map<String, String> jwtBody = new HashMap<>();
            jwtBody.put("Authorization", "Bearer " + token);
            writer.println(mapper.writeValueAsString(jwtBody));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){
        System.out.println("Attempt failed.");
        response.setStatus(401);
    }
}

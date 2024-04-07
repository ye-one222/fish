package com.fisherman.fish.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fisherman.fish.dto.FishDTO;
import com.fisherman.fish.repository.FishRepository;
import com.fisherman.fish.service.FishService;
import com.fisherman.fish.utility.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FishFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final FishService fishService;
    private final long expireMinute = 30;
    private String requestPinNumber;
    private String requestURLPattern = "(\\w | /)*";

    private boolean checkPassword(HttpServletRequest request, HttpServletResponse response){
        FishDTO fish = fishService.findByPinNumber(requestPinNumber);
        System.out.println("FishFilter : checking pw for fish pin '" + requestPinNumber + "'");
        System.out.println("  fish : " + fish);

        // 그물이 없으면 체크할 필요 없음
        if(fish == null){
            System.out.println("FishFilter : no fish for pin " + requestPinNumber);
            return true;
        }
        // 그물이 암호가 걸려있지 않다면 체크할 필요 없음
        if(fishService.findByPinNumber(requestPinNumber).getPassword() == null) {
            System.out.println("FishFilter : no password");
            return true;
        }
        // fishAuthorization 토큰이 맞으면 체크할 필요 없음
        if(checkFishAuthorization(request, response)){
            return true;
        }

        // fishAuthorization이 없는 경우, body에 올바른 fishPassword을 담았는지 확인한다.
        String password = null;
        try {
            // body에서 fishPassword를 파싱한다.
            String jsonString = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            Map<String, String> jsonObject = new ObjectMapper().readValue(jsonString, Map.class);
            System.out.println(jsonString);
            password = jsonObject.get("fishPassword");
            System.out.println("FishFilter : checking password with " + password + " (correct pw : " + fish.getPassword() + "')");
        } catch (Exception e) {
            System.out.println("Exception while parsing body : '" + e.getMessage() + "'");
            return false;
        }
        try{
            // fish의 비밀번호와 일치하는지 확인한다.
            if(!(fish.getPassword().equals(password))){
                // 틀린 경우 reject한다
                System.out.println("FishFilter : wrong password");
                return false;
            }
            // 옳은 경우 header에 토큰을 발급한다.
            // - 유효기간은 10분으로
            System.out.println("FishFilter : correct password");
            response.setHeader("fishAuthorization",
                    "Bearer " + jwtUtil.createJwt(Map.of("pinNumber", requestPinNumber),
                            expireMinute * 60 * 1000L));

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean checkFishAuthorization(HttpServletRequest request, HttpServletResponse response) {
        // 헤더에 fishAuthorization 토큰 올바른지 확인
        String fishAuthorization = request.getHeader("fishAuthorization");
        if(fishAuthorization != null && fishAuthorization.startsWith("Bearer ")){
            String token = "";
            String pin = null;
            try {
                System.out.println("FishFilter : checking header");
                token = fishAuthorization.split(" ")[1];
                pin = (String) jwtUtil.get(token, "pinNumber");
                System.out.println("FishFilter : checking header '" + token + "' with pin " + pin);

                // jwt의 pinNumber와 일치하면 true 반환
                if(pin.equals(requestPinNumber) && !jwtUtil.isExpired(token)) {
                    System.out.println("FishFilter : correct token");
                    return true;
                }
            } catch(ExpiredJwtException e) {
                // 토큰 만료
                System.out.println("FishFilter : '" + e.getMessage() + "'");
            } catch(Exception e) {
                // 토큰 변조 감지
                System.out.println("FishFilter : invalid JWT - '" + e.getMessage() + "'");
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("/fishes/0/files".matches(requestURLPattern));
        if(!request.getRequestURI().matches(requestURLPattern)){
            // 필터링할 url이 아닌 경우 다음 filter로 넘어감
            filterChain.doFilter(request, response);
            return;
        }
        
        System.out.println("FishFilter for '" + request.getRequestURI() + "'");
        System.out.println(request.getRequestURI() + " matches the pattern " + requestURLPattern);

        // URI에서 숫자 뽑아냄
        String numberPattern = "\\d+";
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(request.getRequestURI());
        if(!matcher.find()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }
        requestPinNumber = matcher.group();
        System.out.println("FishFilter for pin number " + requestPinNumber);
        
        // pw 인증 확인
        if(!checkPassword(request, response)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(request, response);
    }

    public void setFilterProcessesUrl(String s) {
        requestURLPattern = s;
    }
}

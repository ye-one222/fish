package com.fisherman.fish.service;

import com.fisherman.fish.entity.CustomUserDetailsEntity;
import com.fisherman.fish.entity.MemberEntity;
import com.fisherman.fish.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);

        if(optionalMemberEntity.isEmpty())
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        MemberEntity memberEntity = optionalMemberEntity.get();


        /* id가 admin이면 ROLE_ADMIN 추가?
        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(id)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return
        return new User(memberEntity.getId(), memberEntity.getPassword(), authorities);

         */
        CustomUserDetailsEntity userDetails = new CustomUserDetailsEntity(memberEntity);
        return userDetails;
    }
}

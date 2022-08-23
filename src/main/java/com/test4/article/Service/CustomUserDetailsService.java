package com.test4.article.Service;


import com.test4.article.domain.Sign;
import com.test4.article.repository.Signrepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final Signrepository signrepository;

    public CustomUserDetailsService(Signrepository signrepository) {
        this.signrepository = signrepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String nickname) {
        return signrepository.findOneWithAuthoritiesByNickname(nickname)
                .map(user -> createUser(nickname, user))
                .orElseThrow(() -> new UsernameNotFoundException(nickname + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String nickname, Sign sign) {


        List<GrantedAuthority> grantedAuthorities = sign.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(sign.getNickname(),
                sign.getPassword(),
                grantedAuthorities);
    }
}

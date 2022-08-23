package com.test4.article.util;

import com.test4.article.domain.Reflush;
import com.test4.article.jwt.TokenProvider;
import com.test4.article.repository.ReflushRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.http.HttpHeaders;
import java.util.Map;
import java.util.Optional;

@Component
public class TokenReflush
{




    public String TokenReflush(Map<String, Object> requestHeader, TokenProvider tokenProvider,ReflushRepository reflushRepository){

        String[] tokens = requestHeader.get("authorization").toString().split(" ");
        String token = tokens[1];
        if(!tokenProvider.validateToken(token)){
            Optional<Reflush> reflush  =reflushRepository.findByKeys(SecurityContextHolder.getContext().getAuthentication().getName());
            if(reflush.isPresent()){
                if(tokenProvider.validateToken(reflush.get().getVal1())){
                    return tokenProvider.createToken(SecurityContextHolder.getContext().getAuthentication());
                }else{
                    reflushRepository.deleteByKeys(SecurityContextHolder.getContext().getAuthentication().getName());
                    return "token_null";
                }
            }
        }
        return "token_true";
    }


}

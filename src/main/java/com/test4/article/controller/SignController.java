package com.test4.article.controller;


import com.test4.article.Dto.ResponseDto;
import com.test4.article.Dto.SignRequestDto;
import com.test4.article.Dto.SigninRequestDto;
import com.test4.article.Service.ArticleSignin;

import com.test4.article.jwt.JwtFilter;
import com.test4.article.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class SignController {
    private final ArticleSignin articlesignin;



    @PostMapping("/signupapi/member/signup")
    public ResponseDto<?>  CreateSignin(@RequestBody SigninRequestDto signinRequestDto){
        return articlesignin.createSign(signinRequestDto);
    }


    @PostMapping("/api/member/login")
    public  ResponseDto<?> Signin(@RequestBody SignRequestDto signRequestDto,HttpServletResponse response){

        return  articlesignin.signin(signRequestDto, response);
    }
}


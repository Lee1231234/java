package com.test4.article.controller;

import com.test4.article.Dto.PostRequestDto;
import com.test4.article.Dto.ResponseDto;
import com.test4.article.Service.PostService;
import com.test4.article.jwt.JwtFilter;
import com.test4.article.jwt.TokenProvider;
import com.test4.article.repository.ReflushRepository;
import com.test4.article.util.TokenReflush;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpHeaders;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class PostController
{
    private final TokenProvider tokenProvider;
    private final PostService postService;
    private final TokenReflush tokenReflush;
    private final ReflushRepository reflushRepository;

        @PostMapping("/post")
        public ResponseDto<?> Posting(@RequestBody PostRequestDto postRequestDto, @RequestHeader Map<String, Object> requestHeader, HttpServletResponse httpServletResponse) {
            String token = tokenReflush.TokenReflush(requestHeader, tokenProvider,reflushRepository);
            if(token.equals("token_null")){
                return ResponseDto.fail("TOKEN_DENIDE","모든 토큰이 만료되었습니다");
            }else if(!token.isEmpty()){
                httpServletResponse.setHeader(JwtFilter.AUTHORIZATION_HEADER,"Access-Token " + token);
            }

            return postService.createPost(postRequestDto);

        }
        @PutMapping("/post/{id}")
        public ResponseDto<?> updatePosting(@RequestBody PostRequestDto postRequestDto, @PathVariable Long id, @RequestHeader Map<String, Object> requestHeader, HttpServletResponse httpServletResponse) {
            String token = tokenReflush.TokenReflush(requestHeader, tokenProvider,reflushRepository);
            if(token.equals("token_null")){
                return ResponseDto.fail("TOKEN_DENIDE","모든 토큰이 만료되었습니다");
            }else if(!token.isEmpty()){
                httpServletResponse.setHeader(JwtFilter.AUTHORIZATION_HEADER,"Access-Token " + token);
            }
            return postService.updatePost(postRequestDto,id);

        }
        @DeleteMapping("/post/{id}")
        public ResponseDto<?> deletePosting(@PathVariable Long id, @RequestHeader Map<String, Object> requestHeader, HttpServletResponse httpServletResponse) {
            String token = tokenReflush.TokenReflush(requestHeader, tokenProvider,reflushRepository);
            if(token.equals("token_null")){
                return ResponseDto.fail("TOKEN_DENIDE","모든 토큰이 만료되었습니다");
            }else if(!token.isEmpty()){
                httpServletResponse.setHeader(JwtFilter.AUTHORIZATION_HEADER,"Access-Token " + token);
            }
            return postService.deletePost(id);

        }


}

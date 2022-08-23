package com.test4.article.controller;

import com.test4.article.Dto.CommentDto;
import com.test4.article.Dto.ResponseDto;
import com.test4.article.Service.CommentService;

import com.test4.article.jwt.JwtFilter;
import com.test4.article.jwt.TokenProvider;
import com.test4.article.repository.ReflushRepository;
import com.test4.article.util.TokenReflush;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {


    private final CommentService commentService;
    private final TokenProvider tokenProvider;
    private final ReflushRepository reflushRepository;
    private final TokenReflush tokenReflush;

    @PostMapping("/auth/comment")
    public ResponseDto<?> createcomment(@RequestBody CommentDto commentDto, @RequestHeader Map<String, Object> requestHeader, HttpServletResponse httpServletResponse) {
        String token = tokenReflush.TokenReflush(requestHeader, tokenProvider,reflushRepository);
        if(token.equals("token_null")){
            return ResponseDto.fail("TOKEN_DENIDE","모든 토큰이 만료되었습니다");
        }else if(!token.isEmpty()){
            httpServletResponse.setHeader(JwtFilter.AUTHORIZATION_HEADER,"Access-Token " + token);
        }

        return commentService.creatComment(commentDto);

    }
    @PutMapping("/auth/comment/{id}")
    public ResponseDto<?> updatecomment(@PathVariable Long  id,@RequestBody CommentDto commentDto,@RequestHeader Map<String, Object> requestHeader, HttpServletResponse httpServletResponse) {
        String token = tokenReflush.TokenReflush(requestHeader, tokenProvider,reflushRepository);
        if(token.equals("token_null")){
            return ResponseDto.fail("TOKEN_DENIDE","모든 토큰이 만료되었습니다");
        }else if(!token.isEmpty()){
            httpServletResponse.setHeader(JwtFilter.AUTHORIZATION_HEADER,"Access-Token " + token);
        }
        return commentService.updateComment(commentDto,id);

    }
    @DeleteMapping("/auth/comment/{id}")
    public ResponseDto<?> deletecomment(@PathVariable Long  id,@RequestHeader Map<String, Object> requestHeader, HttpServletResponse httpServletResponse) {
        String token = tokenReflush.TokenReflush(requestHeader, tokenProvider,reflushRepository);
        if(token.equals("token_null")){
            return ResponseDto.fail("TOKEN_DENIDE","모든 토큰이 만료되었습니다");
        }else if(!token.isEmpty()){
            httpServletResponse.setHeader(JwtFilter.AUTHORIZATION_HEADER,"Access-Token " + token);
        }
        return commentService.deleteComment(id);

    }
}
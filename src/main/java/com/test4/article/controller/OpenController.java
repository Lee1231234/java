package com.test4.article.controller;

import com.test4.article.Dto.PostRequestDto;
import com.test4.article.Dto.ResponseDto;
import com.test4.article.Service.CommentService;
import com.test4.article.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OpenController {
    private final PostService postService;
    private final CommentService commentService;
    @GetMapping("/post")
    public ResponseDto<?> findpost() {
        return postService.findPost();

    }
    @GetMapping("/post/{id}")
    public ResponseDto<?> detailfindpost(@PathVariable long id) {
        return postService.detailfindPost(id);

    }
    @GetMapping("/comment/{id}")
    public ResponseDto<?> findcomment(@PathVariable long id) {
        return commentService.findComment(id);

    }
}

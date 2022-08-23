package com.test4.article.Dto;

import com.test4.article.domain.Comment;
import com.test4.article.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private List<Comment> commentResponeseDtoList;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.commentResponeseDtoList = post.getComments();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();

    }
}

package com.exam.test2.domain;

import com.exam.test2.Dto.BlogResponeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BlognotPassword
{

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String content;
    private String author;
    private Long id;

    public BlognotPassword(BlogResponeDto blogResponeDto ){
        this.createdAt=blogResponeDto.getCreatedAt();
        this.modifiedAt=blogResponeDto.getModifiedAt();
        this.id = blogResponeDto.getId();
        this.title= blogResponeDto.getTitle();
        this.content = blogResponeDto.getContent();
        this.author = blogResponeDto.getAuthor();

    }
}

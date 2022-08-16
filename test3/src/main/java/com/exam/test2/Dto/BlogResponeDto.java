package com.exam.test2.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlogResponeDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String content;
    private String author;
    private Long id;
}

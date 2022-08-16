package com.exam.test2.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlogRequsetDto {

    private String title;
    private String content;
    private String author;
    private String password;


}

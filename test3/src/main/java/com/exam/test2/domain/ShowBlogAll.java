package com.exam.test2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ShowBlogAll {
    private boolean success;
    List<BlognotPassword> data;
    private String error;
    public ShowBlogAll(List<BlognotPassword> blognotPasswords){
        this.success= true;
        this.data = blognotPasswords;
        this.error = null;

    }
}
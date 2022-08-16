package com.exam.test2.domain;

import com.exam.test2.Dto.BlogResponeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Getter
@NoArgsConstructor
public class ShowBlog {
    private boolean success;
    BlognotPassword data;
    private HashMap<String, String> error= new LinkedHashMap<>();
    public ShowBlog(BlogResponeDto blogResponeDto){
        this.success= true;
        this.data = new BlognotPassword(blogResponeDto);
        this.error = null;

    }
    public ShowBlog(boolean a){

        this.success= false;
        this.data = null;
        this.error.put("code" ,"NULL_POST_ID");
        this.error.put("message" ,"post id isn't exist");

    }

}

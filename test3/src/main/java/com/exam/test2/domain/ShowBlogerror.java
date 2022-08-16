package com.exam.test2.domain;

import com.exam.test2.Dto.BlogResponeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@NoArgsConstructor
public class ShowBlogerror {
    private boolean success;
    private Boolean data;
    private HashMap<String, String> error= new LinkedHashMap<>();

    public ShowBlogerror(boolean bool){
        if(bool){
            this.success= false;
            this.data =null;
            this.error.put("code" ,"NULL_POST_ID");
            this.error.put("message" ,"post id isn't exist");

        }else{
            this.success= true;
            this.data = true;
            this.error = null;
        }

    }
}

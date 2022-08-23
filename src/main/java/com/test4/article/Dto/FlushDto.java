package com.test4.article.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlushDto {
    private String key;
    private String value;
    public FlushDto(String key,String value){
        this.key =key;
        this.value = value;
    }
}

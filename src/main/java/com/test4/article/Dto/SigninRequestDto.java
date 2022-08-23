package com.test4.article.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninRequestDto {
    private String nickname;
    private String password;
    private String passwordConfirm;
}

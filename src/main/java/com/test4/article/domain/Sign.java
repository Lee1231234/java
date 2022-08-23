package com.test4.article.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test4.article.Dto.SignRequestDto;
import com.test4.article.Dto.SigninRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sign extends Timestamped{

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;



    @JsonIgnore
    @Column(nullable = false)
    private String password;



    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public Sign(SignRequestDto signRequestDto){
        this.nickname = signRequestDto.getNickname();
        this.password = signRequestDto.getPassword();
    }
    public Sign(SigninRequestDto signinRequestDto){
        this.nickname = signinRequestDto.getNickname();
        this.password = signinRequestDto.getPassword();

    }
}

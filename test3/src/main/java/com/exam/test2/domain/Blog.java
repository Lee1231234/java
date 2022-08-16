package com.exam.test2.domain;

import com.exam.test2.Dto.BlogRequsetDto;
import com.exam.test2.Dto.BlogResponeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Blog extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private  Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String password;

    public Blog(String title,String content,String author,String password){
        this.title = title;
        this.content= content;
        this.author = author;
        this.password=password;
    }
    public Blog(BlogRequsetDto blogRequsetDto){
        this.title = blogRequsetDto.getTitle();
        this.content= blogRequsetDto.getContent();
        this.author = blogRequsetDto.getAuthor();
        this.password = blogRequsetDto.getPassword();
    }
    public void update(BlogRequsetDto blogRequsetDto){
        this.title = blogRequsetDto.getTitle();
        this.content= blogRequsetDto.getContent();
        this.author = blogRequsetDto.getAuthor();
        this.password = blogRequsetDto.getPassword();
    }
    public void response(BlogResponeDto blogResponeDto){
        blogResponeDto.setCreatedAt(this.getCreatedAt());
        blogResponeDto.setModifiedAt(this.getModifiedAt());
        blogResponeDto.setId(this.id);
        blogResponeDto.setTitle(this.title);
        blogResponeDto.setContent(this.content);
        blogResponeDto.setAuthor(this.author);
    }

}

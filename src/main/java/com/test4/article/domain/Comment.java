package com.test4.article.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test4.article.Dto.CommentDto;
import com.test4.article.repository.Postrepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends  Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;


    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne(targetEntity = Post.class,fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    public Comment(CommentDto commentDto){

        this.post.setId(commentDto.getPostId());
        this.content = commentDto.getContent();
    }


    public Comment(CommentDto commentDto, Postrepository postrepository) {

        this.post =  postrepository.findById(commentDto.getPostId()).get();
        this.content = commentDto.getContent();
    }
}

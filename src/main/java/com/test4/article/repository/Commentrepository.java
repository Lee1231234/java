package com.test4.article.repository;

import com.test4.article.domain.Comment;
import com.test4.article.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Commentrepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllBypost_id(Long id);

    void deleteBypost_id(Long id);


    Optional<Comment> findBypost_idAndId(Long id,Long post_id);


    Optional<Comment> findById(Long id);
}

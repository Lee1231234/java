package com.test4.article.repository;

import com.test4.article.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Postrepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
}

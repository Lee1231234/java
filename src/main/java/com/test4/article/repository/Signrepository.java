package com.test4.article.repository;


import com.test4.article.domain.Sign;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Signrepository extends JpaRepository<Sign, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<Sign> findOneWithAuthoritiesByNickname(String username);
    Optional<Sign> findAllBynickname(String nickname);

}

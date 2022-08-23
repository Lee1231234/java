package com.test4.article.repository;

import com.test4.article.domain.Reflush;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReflushRepository extends JpaRepository<Reflush,Long> {
    Optional<Reflush> findByKeys(String Keys);

    void deleteByKeys(String Keys);
}

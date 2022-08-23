package com.test4.article;

import com.test4.article.domain.Authority;
import com.test4.article.repository.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class ArticleApplication {

    public static void main(String[] args) {

        SpringApplication.run(ArticleApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(AuthorityRepository authorityRepository) {
        return (args) -> {
            authorityRepository.save(new Authority("test"));
            authorityRepository.save(new Authority("admin"));
            authorityRepository.save(new Authority("user"));



        };
    }

}

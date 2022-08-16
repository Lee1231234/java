package com.exam.test2.service;

import com.exam.test2.domain.Blog;
import com.exam.test2.domain.BlogRepository;
import com.exam.test2.Dto.BlogRequsetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;


    @Transactional
    public Blog update(Long id, BlogRequsetDto blogRequsetDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        blog.update(blogRequsetDto);
        return blog;
    }
}

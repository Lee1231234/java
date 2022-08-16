package com.exam.test2.controller;

import com.exam.test2.Dto.BlogRequsetDto;
import com.exam.test2.Dto.BlogResponeDto;
import com.exam.test2.domain.*;
import com.exam.test2.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @PostMapping("/api/post")
    public ShowBlog createBlog(@RequestBody BlogRequsetDto blogRequsetDto) {
        Blog blog = new Blog(blogRequsetDto);
        blogRepository.save(blog);
        BlogResponeDto blogResponeDto = new BlogResponeDto();
        blog.response(blogResponeDto);
        ShowBlog bnonP = new ShowBlog(blogResponeDto);


        return bnonP;
    }



    @GetMapping("/api/post")
    public ShowBlogAll getBlogs(){
        List<Blog> blog =blogRepository.findAllByOrderByModifiedAtDesc();
        List<BlognotPassword> bnotP = new ArrayList<>();
        for(Blog i :blog){
            BlogResponeDto blogResponeDto = new BlogResponeDto();
            i.response(blogResponeDto);
            bnotP.add(new BlognotPassword(blogResponeDto));
        }
        ShowBlogAll blogs =new ShowBlogAll(bnotP);

        return blogs;}

    @GetMapping("/api/post/{id}")
    public  ShowBlog getBlog(@PathVariable Long id){

    //    Blog blog =blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("에러났음"));
        Optional<Blog> blog=blogRepository.findById(id);
        ShowBlog blog1;
        if(blog.isEmpty()){
            blog1 = new ShowBlog(true);
        }else{
            Blog blog2 = blog.get();
            BlogResponeDto blogResponeDto = new BlogResponeDto();
            blog2.response(blogResponeDto);
            blog1 = new ShowBlog(blogResponeDto);
        }
        return blog1;
    }

    @PostMapping("/api/post/{id}")
    public ShowBlogerror PasswordCheck(@PathVariable Long id,@RequestBody BlogRequsetDto blogRequsetDto){
        Optional<Blog> blog=blogRepository.findById(id);
        ShowBlogerror blog1;

        if(blog.isEmpty()){
            blog1 = new ShowBlogerror(true);
        }else{


            if(blogRequsetDto.getPassword().equals(blog.get().getPassword())) {

                blog1 = new ShowBlogerror(false);
            }
            else
                blog1 = new ShowBlogerror(true);
        }
        return blog1;

    }
    @PutMapping("/api/post/{id}")
    public ShowBlog modifyblog(@PathVariable Long id, @RequestBody BlogRequsetDto blogRequsetDto){
        ShowBlog blog1;
        if(blogRepository.existsById(id)){
            Blog blog=blogService.update(id,blogRequsetDto);
            BlogResponeDto blogResponeDto = new BlogResponeDto();
            blog.response(blogResponeDto);
            blog1 = new ShowBlog(blogResponeDto);
        }else{
            blog1 = new ShowBlog(true);
        }


        return blog1;
    }





    @DeleteMapping("/api/post/{id}")
    public ShowBlogerror delblog(@PathVariable Long id) {
        ShowBlogerror blog1;
        if(blogRepository.existsById(id)){
            blogRepository.deleteById(id);
            blog1 = new ShowBlogerror(false);
        }else{
            blog1 = new ShowBlogerror(true);
        }


        return blog1;
    }

}



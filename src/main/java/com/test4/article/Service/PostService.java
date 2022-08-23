package com.test4.article.Service;


import com.test4.article.Dto.PostRequestDto;
import com.test4.article.Dto.PostResponseDto;
import com.test4.article.Dto.ResponseDto;

import com.test4.article.domain.Post;
import com.test4.article.domain.Sign;
import com.test4.article.jwt.JwtFilter;
import com.test4.article.jwt.TokenProvider;
import com.test4.article.repository.Commentrepository;
import com.test4.article.repository.Postrepository;
import com.test4.article.repository.Signrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final TokenProvider tokenProvider;
    private final Postrepository postrepository;
    private final Signrepository signrepository;
    private final Commentrepository commentrepository;
    @Transactional
    public ResponseDto<?> createPost(PostRequestDto postRequestDto) {

        Post post =new Post(postRequestDto);
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        post.setAuthor(name);
        post.setMember(signrepository.findAllBynickname(name).get());
        postrepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return ResponseDto.success(postResponseDto);
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> findPost() {
        List<Post> postList =postrepository.findAllByOrderByModifiedAtDesc();

        return ResponseDto.success(postList);
    }


    @Transactional(readOnly = true)
    public ResponseDto<?> detailfindPost(long id) {
        Optional<Post> post =postrepository.findById(id);
        if(post.isPresent()){
            PostResponseDto postResponseDto = new PostResponseDto(post.get());
            return ResponseDto.success(postResponseDto);
        }else{
            return ResponseDto.fail("NOT_EXIST_ID","포스트가 존재하지 않습니다.");
        }

    }

    @Transactional
    public ResponseDto<?> updatePost(PostRequestDto postRequestDto,long id) {
        Optional<Post> post = postrepository.findById(id);
        if(post.isPresent()){
           if(post.get().getAuthor().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
               post.get().setTitle(postRequestDto.getTitle());
               post.get().setContent(postRequestDto.getContent());
               return ResponseDto.success(post);
            }else{
               return ResponseDto.fail("NOT_Allow_ID","작성자만 변경할수있습니다.");
           }

        }else{
            return ResponseDto.fail("NOT_EXIST_ID","포스트가 존재하지 않습니다.");
        }
    }
    @Transactional
    public ResponseDto<?> deletePost(long id) {
        Optional<Post> post = postrepository.findById(id);
        if(post.isPresent()){
            if(post.get().getAuthor().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                postrepository.deleteById(id);
                commentrepository.deleteBypost_id(id);
                //댓글도 다 삭제해야함.
                String success = "delete success";
                return ResponseDto.success(success);
            }else{
                return ResponseDto.fail("NOT_Allow_ID","작성자만 삭제할수있습니다.");
            }
        }else{
            return ResponseDto.fail("NOT_EXIST_ID","포스트가 존재하지 않습니다.");
        }
    }
}

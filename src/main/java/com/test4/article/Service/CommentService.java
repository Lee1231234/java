package com.test4.article.Service;

import com.test4.article.Dto.CommentDto;
import com.test4.article.Dto.ResponseDto;
import com.test4.article.domain.Comment;
import com.test4.article.domain.Post;
import com.test4.article.repository.Commentrepository;
import com.test4.article.repository.Postrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final Commentrepository commentrepository;
    private final Postrepository postrepository;


    @Transactional
    public ResponseDto<?> creatComment(CommentDto commentDto) {
        Optional<Post> post1 =postrepository.findById(commentDto.getPostId());
        if(post1.isEmpty()) {
            return ResponseDto.fail("NOT_EXIST_POST","포스트가 존재하지 않습니다.");
        }
        Comment comment = new Comment(commentDto,postrepository);
        comment.setAuthor( SecurityContextHolder.getContext().getAuthentication().getName());
        commentrepository.save(comment);
        Post post = postrepository.findById(commentDto.getPostId()).get();
        post.addComment(comment);

        return ResponseDto.success(comment);
    }

   @Transactional(readOnly = true)
    public ResponseDto<?> findComment(long id) {
       List<Comment> comment =  commentrepository.findAllBypost_id(id);
       return ResponseDto.success(comment);
    }

    @Transactional
    public ResponseDto<?> updateComment(CommentDto commentDto, Long id) {
        Optional<Comment> comment = commentrepository.findBypost_idAndId(id,commentDto.getPostId());

        if(comment.isPresent()){
            if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(comment.get().getAuthor())){
                return ResponseDto.fail("NOT_Allow_ID","작성자만 변경할수있습니다.");
            }
            comment.get().setContent(commentDto.getContent());
            return  ResponseDto.success(comment);
        }else{
            return ResponseDto.fail("NOT_EXIST_COMMENT","코멘트가 존재하지 않습니다.");
        }
    }

    @Transactional
    public ResponseDto<?> deleteComment(Long id) {
        Optional<Comment> comment = commentrepository.findById(id);
        if(comment.isPresent()){
            if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(comment.get().getAuthor())){
                return ResponseDto.fail("NOT_Allow_ID","작성자만 삭제할수있습니다.");
            }
            commentrepository.deleteById(id);
            return  ResponseDto.success("success");
        }else{
            return ResponseDto.fail("NOT_EXIST_COMMENT","코멘트가 존재하지 않습니다.");
        }
    }
}

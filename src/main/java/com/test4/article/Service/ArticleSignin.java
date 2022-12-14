package com.test4.article.Service;

import antlr.Token;
import com.test4.article.Dto.FlushDto;
import com.test4.article.Dto.ResponseDto;
import com.test4.article.Dto.SignRequestDto;
import com.test4.article.Dto.SigninRequestDto;
import com.test4.article.domain.Authority;
import com.test4.article.domain.Reflush;
import com.test4.article.domain.Sign;
import com.test4.article.jwt.JwtFilter;
import com.test4.article.jwt.TokenProvider;
import com.test4.article.repository.AuthorityRepository;
import com.test4.article.repository.ReflushRepository;
import com.test4.article.repository.Signrepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class ArticleSignin {
    private final ReflushRepository reflushRepository;
    private final Signrepository signrepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @Transactional
    public ResponseDto<?> createSign(SigninRequestDto signinRequestDto) {

        Optional<Sign> SignCheck = signrepository.findAllBynickname(signinRequestDto.getNickname());
        if(SignCheck.isPresent()){
            return ResponseDto.fail("Id_IS_EXIST", "???????????? ?????? ???????????????");
        }
        if(!signinRequestDto.getPassword().equals(signinRequestDto.getPasswordConfirm())){
            return ResponseDto.fail("PASSWORD_NOT_CONFIRM", "??????????????? ???????????? ????????????.");
        }
        boolean test1 =Pattern.matches("^[a-zA-Z0-9]{4,12}$",signinRequestDto.getNickname());
        boolean test2 =Pattern.matches("^[a-z0-9]{4,32}$",signinRequestDto.getPassword());

        if(!test1){
            return ResponseDto.fail("NICKNAME_OR_PASSWORD_NOT_CURRENT", "??????????????? ??????????????? ????????? ?????? ????????????.");
        }
        if(!test2){
            return ResponseDto.fail("NICKNAME_OR_PASSWORD_NOT_CURRENT", "??????????????? ??????????????? ????????? ?????? ????????????.");
        }

       signinRequestDto.setPassword(passwordEncoder.encode(signinRequestDto.getPassword()));
        Sign sign = new Sign(signinRequestDto);
        Set<Authority> authorities= new HashSet<>();


        authorities.add(new Authority("test"));
        sign.setAuthorities(authorities);
        signrepository.save(sign);
        return ResponseDto.success(sign);
    }

    @Transactional
    public ResponseDto<?> signin(SignRequestDto signRequestDto,HttpServletResponse response) {
        Optional<Sign> SignCheck = signrepository.findAllBynickname(signRequestDto.getNickname());
        Sign sign = SignCheck.get();
        if(passwordEncoder.matches(signRequestDto.getPassword(),sign.getPassword())){
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(signRequestDto.getNickname(), signRequestDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.createToken(authentication);
            String reflushtoken = tokenProvider.flash_createToken(authentication);
            //?????? ?????? ????????? ??????..
            //????????? ????????? ????????? ???????????????.
            response.setHeader(JwtFilter.AUTHORIZATION_HEADER,"Access-Token " + jwt);
            //??? ????????? ?????? ???????????? ????????? ????????? ????????? ??????.
          //  Cookie AccessToken = new Cookie("Access-Token",jwt);

           // AccessToken.setHttpOnly(true);
          //  AccessToken.setPath("/");
          //  response.addCookie(AccessToken);

            //flush ?????? ??????

            FlushDto flushDto = new FlushDto(signRequestDto.getNickname(),reflushtoken);
            Reflush reflush =new Reflush(flushDto);

            Optional<Reflush> reflush1 = reflushRepository.findByKeys(signRequestDto.getNickname());
            if(reflush1.isEmpty())
                reflushRepository.save(reflush);
            else{
                reflushRepository.delete(reflush1.get());
                reflushRepository.save(reflush);
            }

            return ResponseDto.success(sign);
        }else{
            return ResponseDto.fail("WRONG_ID_PASSWORD", "???????????? ??????????????? ???????????? ????????????.");
        }

    }
}

package com.test4.article.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test4.article.Dto.ResponseDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {
      // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
      setResponse(response);


   }
   private void setResponse(HttpServletResponse response) throws IOException {
      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

      ObjectMapper mapper = new ObjectMapper();
      String code = mapper.writeValueAsString(ResponseDto.fail("BAD_REQUEST","로그인이 필요합니다."));

      response.getWriter().println(code);
   }
}

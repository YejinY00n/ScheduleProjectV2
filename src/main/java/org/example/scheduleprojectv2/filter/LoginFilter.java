package org.example.scheduleprojectv2.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.util.PatternMatchUtils;

public class LoginFilter implements Filter {

  // 필터 검사 통과 URL
  private static final String[] WHITE_LIST = {"/users/signup", "/users/login"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    // Filter 에서 수행할 Logic
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();

    // 로그인 필요 URL
    if(!isWhiteList(requestURI)) {
      // 세션 로그인 확인
      HttpSession session = httpRequest.getSession(false);

      // 로그인 하지 않은 경우
      if(session == null || session.getId() == null) {
        throw new RuntimeException("로그인 해주세요.");
      }
    }

    // Chain 이 없으면 Servlet 호출
    chain.doFilter(request, response);
  }

  // 로그인 여부 확인
  boolean isWhiteList(String requestURI) {
    return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
  }
}

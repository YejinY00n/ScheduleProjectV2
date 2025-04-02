package org.example.scheduleprojectv2.config;

import jakarta.servlet.Filter;
import org.example.scheduleprojectv2.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public FilterRegistrationBean customFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

    // Filter 등록
    filterRegistrationBean.setFilter(new LoginFilter());
    // Filter 순서 설정
    filterRegistrationBean.setOrder(1);
    // 적용 대상 URL
    filterRegistrationBean.addUrlPatterns("/*");

    return filterRegistrationBean;
  }
}

package com.cyan.config;

import com.cyan.filter.CyanFilter;
import com.cyan.interceptors.CyanInterceptor;
import com.cyan.servlet.CyanServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CyanWebConfig implements WebMvcConfigurer {

    @Autowired
    private CyanInterceptor cyanInterceptor;

    @Bean
    public ServletRegistrationBean<CyanServlet> cyanServlet(){
        ServletRegistrationBean<CyanServlet> servletRegistrationBean = new ServletRegistrationBean(new CyanServlet(),"/cyan");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CyanFilter> cyanFilter(){
        FilterRegistrationBean<CyanFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new CyanFilter());
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(cyanInterceptor).addPathPatterns("/**").excludePathPatterns("/index.html","/");
    }
}

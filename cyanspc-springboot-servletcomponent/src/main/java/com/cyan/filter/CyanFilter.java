package com.cyan.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by cyan
 * Date:2019/8/12 20:10
 */
public class CyanFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("cyanFilter的doFilter方法");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

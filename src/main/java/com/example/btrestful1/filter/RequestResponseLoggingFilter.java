package com.example.btrestful1.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

    private static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        LOG.info("Initializing filter :{}", this);
    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);

        LOG.info("Logging Response :{}", res.getContentType());
    }

    @Override
    public void destroy() {

        LOG.warn("Destructing filter :{}", this);
    }
}

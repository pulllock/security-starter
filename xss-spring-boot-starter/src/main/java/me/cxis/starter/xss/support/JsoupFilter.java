package me.cxis.starter.xss.support;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsoupFilter extends OncePerRequestFilter {


    private final JsoupProcessor jsoupProcessor;

    public JsoupFilter(String whitelist) {
        // 初始化JsoupProcessor
        this.jsoupProcessor = new JsoupProcessor(whitelist);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new JsoupHttpServletRequestWrapper(request, jsoupProcessor), response);
    }
}

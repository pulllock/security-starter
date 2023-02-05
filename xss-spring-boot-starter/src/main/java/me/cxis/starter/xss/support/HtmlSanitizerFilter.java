package me.cxis.starter.xss.support;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HtmlSanitizerFilter extends OncePerRequestFilter {

    private String allowElements;

    public HtmlSanitizerFilter(String allowElements) {
        this.allowElements = allowElements;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new HtmlSanitizerHttpServletRequestWrapper(request, allowElements), response);
    }
}

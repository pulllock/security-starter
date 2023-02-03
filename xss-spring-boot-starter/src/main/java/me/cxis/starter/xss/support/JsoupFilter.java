package me.cxis.starter.xss.support;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsoupFilter extends OncePerRequestFilter {

    private String whitelist;

    public JsoupFilter(String whitelist) {
        this.whitelist = whitelist;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new JsoupHttpServletRequestWrapper(request, whitelist), response);
    }
}

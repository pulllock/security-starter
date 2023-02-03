package me.cxis.starter.xss.support;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AntiSamyFilter extends OncePerRequestFilter {

    private String policyFile;

    public AntiSamyFilter(String policyFile) {
        this.policyFile = policyFile;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new AntiSamyHttpServletRequestWrapper(request, policyFile), response);
    }
}

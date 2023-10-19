package fun.pullock.starter.xss.support;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EsapiFilter extends OncePerRequestFilter {

    private final EsapiProcessor esapiProcessor;

    public EsapiFilter() {
        // 初始化EsapiProcessor
        this.esapiProcessor = new EsapiProcessor();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new EsapiHttpServletRequestWrapper(request, esapiProcessor), response);
    }
}

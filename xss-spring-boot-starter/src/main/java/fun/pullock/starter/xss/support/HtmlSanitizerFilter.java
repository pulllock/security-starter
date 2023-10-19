package fun.pullock.starter.xss.support;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HtmlSanitizerFilter extends OncePerRequestFilter {

    private HtmlSanitizerProcessor htmlSanitizerProcessor = null;

    public HtmlSanitizerFilter(String allowElements) {
        // 初始化HtmlSanitizerProcessor
        this.htmlSanitizerProcessor = new HtmlSanitizerProcessor(allowElements);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new HtmlSanitizerHttpServletRequestWrapper(request, htmlSanitizerProcessor), response);
    }
}

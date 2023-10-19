package fun.pullock.starter.xss.support;

import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

public class EsapiHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] cachedContent;

    private final EsapiProcessor esapiProcessor;

    public EsapiHttpServletRequestWrapper(HttpServletRequest request, EsapiProcessor esapiProcessor) throws IOException {
        super(request);
        this.esapiProcessor = esapiProcessor;

        // 获取原始请求体的内容
        byte[] originContent = StreamUtils.copyToByteArray(request.getInputStream());

        if (originContent.length > 0) {
            String contentType = request.getContentType();
            // 文件上传的流不处理
            if (contentType != null && !contentType.equals(MULTIPART_FORM_DATA_VALUE)) {
                cachedContent = this.esapiProcessor.encodeForHtml(new String(originContent, UTF_8)).getBytes(UTF_8);
            } else {
                cachedContent = originContent;
            }
        } else {
            cachedContent = originContent;
        }
    }

    /**
     * 对Header的内容进行过滤
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (!StringUtils.hasLength(value)) {
            return value;
        }
        return encodeForHtml(value);
    }

    /**
     * 对参数内容进行过滤
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (!StringUtils.hasLength(value)) {
            return value;
        }
        return encodeForHtml(value);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        parameterMap.forEach((k, v) -> {
            if (v != null && v.length > 0) {
                for (int i = 0; i < v.length; i++) {
                    v[i] = encodeForHtml(v[i]);
                }
            }
        });
        return parameterMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null || values.length == 0) {
            return values;
        }

        for (int i = 0; i < values.length; i++) {
            values[i] = encodeForHtml(values[i]);
        }
        return values;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ContentCachingInputStream(cachedContent);
    }

    @Override
    public String getCharacterEncoding() {
        String enc = super.getCharacterEncoding();
        return (enc != null ? enc : WebUtils.DEFAULT_CHARACTER_ENCODING);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream(), getCharacterEncoding()));
    }

    public byte[] getCachedContent() {
        return cachedContent;
    }

    private static class ContentCachingInputStream extends ServletInputStream {

        private final ByteArrayInputStream is;

        public ContentCachingInputStream(byte[] cachedContent) {
            is = new ByteArrayInputStream(cachedContent);
        }

        @Override
        public boolean isFinished() {
            return this.is.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
            return is.read();
        }
    }

    private String encodeForHtml(String originContent) {
        return esapiProcessor.encodeForHtml(originContent);
    }
}

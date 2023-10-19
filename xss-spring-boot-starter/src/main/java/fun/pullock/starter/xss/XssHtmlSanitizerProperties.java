package fun.pullock.starter.xss;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.xss.html-sanitizer")
public class XssHtmlSanitizerProperties {

    /**
     * 是否启用html sanitizer xss过滤器
     */
    private boolean enable = true;

    /**
     * html sanitizer允许的标签
     */
    private String allowElements;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getAllowElements() {
        return allowElements;
    }

    public void setAllowElements(String allowElements) {
        this.allowElements = allowElements;
    }
}

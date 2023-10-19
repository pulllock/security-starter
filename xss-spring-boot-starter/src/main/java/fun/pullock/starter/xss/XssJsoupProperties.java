package fun.pullock.starter.xss;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.xss.jsoup")
public class XssJsoupProperties {

    /**
     * 是否启用jsoup xss过滤器
     */
    private boolean enable = true;

    /**
     * jsoup白名单
     */
    private String whitelist;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }
}

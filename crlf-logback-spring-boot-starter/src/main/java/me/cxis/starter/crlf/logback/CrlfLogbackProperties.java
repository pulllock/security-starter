package me.cxis.starter.crlf.logback;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.crlf-logback")
public class CrlfLogbackProperties {

    /**
     * 是否启用crlf logback过滤器
     */
    private boolean enable = true;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

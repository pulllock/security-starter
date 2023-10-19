package fun.pullock.starter.crlf.log4j;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.crlf-log4j")
public class CrlfLog4jProperties {

    /**
     * 是否启用crlf log4j过滤器
     */
    private boolean enable = true;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

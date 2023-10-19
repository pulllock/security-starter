package fun.pullock.starter.xss;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.xss.esapi")
public class XssEsapiProperties {

    /**
     * 是否启用esapi xss过滤器
     */
    private boolean enable = true;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

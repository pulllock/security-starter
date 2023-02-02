package me.cxis.starter.cros;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.cros")
public class CrosProperties {

    /**
     * 是否启用cros过滤器
     */
    private boolean enable = true;


}

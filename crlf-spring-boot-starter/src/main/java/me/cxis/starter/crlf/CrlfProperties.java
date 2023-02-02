package me.cxis.starter.crlf;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.crlf")
public class CrlfProperties {

    /**
     * 是否启用crlf过滤器
     */
    private boolean enable = true;


}

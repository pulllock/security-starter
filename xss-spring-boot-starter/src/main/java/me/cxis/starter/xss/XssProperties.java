package me.cxis.starter.xss;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.xss")
public class XssProperties {

    /**
     * 是否启用xss过滤器
     */
    private boolean enable = true;


}

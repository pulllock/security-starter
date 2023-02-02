package me.cxis.starter.sql;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.sql")
public class SqlProperties {

    /**
     * 是否启用sql注入过滤器
     */
    private boolean enable = true;


}

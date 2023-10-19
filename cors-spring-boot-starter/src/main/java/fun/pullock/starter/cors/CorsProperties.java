package fun.pullock.starter.cors;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.starter.cors")
public class CorsProperties {

    /**
     * 是否启用cors过滤器
     */
    private boolean enable = true;


}

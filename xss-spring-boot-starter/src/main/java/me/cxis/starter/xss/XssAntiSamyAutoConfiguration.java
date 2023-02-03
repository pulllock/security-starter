package me.cxis.starter.xss;

import me.cxis.starter.xss.support.AntiSamyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@EnableConfigurationProperties(XssAntiSamyProperties.class)
public class XssAntiSamyAutoConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(XssAntiSamyAutoConfiguration.class);

    private XssAntiSamyProperties xssAntiSamyProperties;

    public XssAntiSamyAutoConfiguration(XssAntiSamyProperties xssAntiSamyProperties) {
        this.xssAntiSamyProperties = xssAntiSamyProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "security.starter.xss.antisamy", value = {"enable"}, havingValue = "true")
    @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
    public AntiSamyFilter antiSamyFilter() {
        LOGGER.info("antisamy filter enabled");
        return new AntiSamyFilter(xssAntiSamyProperties.getPolicyFile());
    }
}

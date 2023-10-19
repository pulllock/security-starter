package fun.pullock.starter.xss;

import fun.pullock.starter.xss.support.JsoupFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@EnableConfigurationProperties(XssJsoupProperties.class)
public class XssJsoupAutoConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(XssJsoupAutoConfiguration.class);

    private XssJsoupProperties xssJsoupProperties;

    public XssJsoupAutoConfiguration(XssJsoupProperties xssJsoupProperties) {
        this.xssJsoupProperties = xssJsoupProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "security.starter.xss.jsoup", value = {"enable"}, havingValue = "true")
    @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
    public JsoupFilter jsoupFilter() {
        LOGGER.info("jsoup filter enabled");
        return new JsoupFilter(xssJsoupProperties.getWhitelist());
    }
}

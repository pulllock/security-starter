package me.cxis.starter.xss;

import me.cxis.starter.xss.support.HtmlSanitizerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@EnableConfigurationProperties(XssHtmlSanitizerProperties.class)
public class XssHtmlSanitizerAutoConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(XssHtmlSanitizerAutoConfiguration.class);

    private XssHtmlSanitizerProperties xssHtmlSanitizerProperties;

    public XssHtmlSanitizerAutoConfiguration(XssHtmlSanitizerProperties xssHtmlSanitizerProperties) {
        this.xssHtmlSanitizerProperties = xssHtmlSanitizerProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "security.starter.xss.html-sanitizer", value = {"enable"}, havingValue = "true")
    @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
    public HtmlSanitizerFilter htmlSanitizerFilter() {
        LOGGER.info("html sanitizer filter enabled");
        return new HtmlSanitizerFilter(xssHtmlSanitizerProperties.getAllowElements());
    }
}

package fun.pullock.starter.xss;

import fun.pullock.starter.xss.support.EsapiFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@EnableConfigurationProperties(XssEsapiProperties.class)
public class XssEsapiAutoConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(XssEsapiAutoConfiguration.class);

    private XssEsapiProperties xssEsapiProperties;

    public XssEsapiAutoConfiguration(XssEsapiProperties xssEsapiProperties) {
        this.xssEsapiProperties = xssEsapiProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "security.starter.xss.esapi", value = {"enable"}, havingValue = "true")
    @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
    public EsapiFilter esapiFilter() {
        LOGGER.info("esapi filter enabled");
        return new EsapiFilter();
    }
}

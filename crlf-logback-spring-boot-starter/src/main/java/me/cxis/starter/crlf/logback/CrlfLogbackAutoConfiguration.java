package me.cxis.starter.crlf.logback;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CrlfLogbackProperties.class)
public class CrlfLogbackAutoConfiguration {

}

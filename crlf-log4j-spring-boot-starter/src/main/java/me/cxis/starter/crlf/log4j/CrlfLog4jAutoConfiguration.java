package me.cxis.starter.crlf.log4j;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CrlfLog4jProperties.class)
public class CrlfLog4jAutoConfiguration {

}

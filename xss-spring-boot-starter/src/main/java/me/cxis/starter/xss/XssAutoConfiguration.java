package me.cxis.starter.xss;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(XssProperties.class)
public class XssAutoConfiguration {

}

package me.cxis.starter.cors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class CorsAutoConfiguration {

}

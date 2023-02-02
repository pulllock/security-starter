package me.cxis.starter.cros;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CrosProperties.class)
public class CrosAutoConfiguration {

}

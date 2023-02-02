package me.cxis.starter.sql;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SqlProperties.class)
public class SqlAutoConfiguration {

}

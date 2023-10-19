package fun.pullock.starter.crlf;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CrlfProperties.class)
public class CrlfAutoConfiguration {

}

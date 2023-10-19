package fun.pullock.starter.sample.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 尽量不要使用*
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PATCH", "DELETE", "PUT")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
}

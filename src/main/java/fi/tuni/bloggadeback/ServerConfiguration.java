package fi.tuni.bloggadeback;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is used for configuring server
 */
@Configuration
public class ServerConfiguration implements WebMvcConfigurer {

    /**
     * Sets allowed origins and allowed methods
     *
     * @param registry Cors registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://localhost:3000", "http://localhost:3000", "https://bloggade.herokuapp.com", "https://localhost:8080", "https://localhost:8080"
                )
                .allowedMethods("POST", "GET", "DELETE", "PUT").allowCredentials(true);
    }
}

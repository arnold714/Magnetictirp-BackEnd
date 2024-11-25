package com.ssafy.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		  log.info("Configuring CORS mappings...");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
}
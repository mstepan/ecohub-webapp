package com.github.kostia.ecohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ConfigurationPropertiesScan
@SpringBootApplication
public class EcohubApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcohubApplication.class, args);
    }

}

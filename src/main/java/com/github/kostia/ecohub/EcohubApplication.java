package com.github.kostia.ecohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class EcohubApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcohubApplication.class, args);
    }

}

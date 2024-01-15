package com.github.kostia.ecohub.config;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Accessors(fluent = false, chain = false)
@ConfigurationProperties(prefix = "ecohub")
public final class EcohubConfig {

    private String domainUrl;

}


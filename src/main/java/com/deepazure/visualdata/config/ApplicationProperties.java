package com.deepazure.visualdata.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "spring.application")
public class ApplicationProperties {

    private long tokenExpire;

    private String client;

    private String processName;

    private boolean startClient;

}

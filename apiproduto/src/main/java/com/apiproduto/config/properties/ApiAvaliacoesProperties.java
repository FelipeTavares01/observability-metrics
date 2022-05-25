package com.apiproduto.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "apiavaliacoes")
public class ApiAvaliacoesProperties {

    @Getter
    @Setter
    private String url;
}

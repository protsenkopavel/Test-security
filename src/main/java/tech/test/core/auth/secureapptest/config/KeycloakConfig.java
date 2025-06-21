package tech.test.core.auth.secureapptest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak.client")
public class KeycloakConfig {

    private String serverUrl;

    private String applicationRealm;

    private String clientSecret;

    private String clientId;

}


package tech.test.core.auth.secureapptest.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;
import tech.test.core.auth.secureapptest.app.dto.LoginRq;
import tech.test.core.auth.secureapptest.app.dto.TokenRs;
import tech.test.core.auth.secureapptest.app.service.OAuthService;
import tech.test.core.auth.secureapptest.config.KeycloakConfig;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakService implements OAuthService {

    private final KeycloakConfig keycloakConfig;

    public TokenRs authenticateUser(LoginRq loginRq) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakConfig.getServerUrl())
                .realm(keycloakConfig.getApplicationRealm())
                .clientId(keycloakConfig.getClientId())
                .clientSecret(keycloakConfig.getClientSecret())
                .username(loginRq.login())
                .password(loginRq.password())
                .grantType(OAuth2Constants.PASSWORD)
                .build();

        AccessTokenResponse token = keycloak.tokenManager().getAccessToken();

        return new TokenRs(
                token.getToken(),
                token.getExpiresIn(),
                token.getRefreshToken(),
                token.getRefreshExpiresIn()
        );
    }

}

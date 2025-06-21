package tech.test.core.auth.secureapptest.app.service;

import tech.test.core.auth.secureapptest.app.dto.LoginRq;
import tech.test.core.auth.secureapptest.app.dto.TokenRs;

public interface OAuthService {

    /**
     * Аутентифицирует существующего в системе пользователя.
     *
     * @param loginRq объект, содержащий данные для аутентификации (логин и пароль пользователя).
     * @return {@link TokenRs} объект, содержащий данные для доступа (accessToken и refreshToken).
     */
    TokenRs authenticateUser(LoginRq loginRq);

}

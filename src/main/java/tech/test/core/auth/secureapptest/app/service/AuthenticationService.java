package tech.test.core.auth.secureapptest.app.service;

import tech.test.core.auth.secureapptest.app.dto.LoginRq;
import tech.test.core.auth.secureapptest.app.dto.TokenRs;
import tech.test.core.auth.secureapptest.app.dto.UserDetailsDto;
import tech.test.core.auth.secureapptest.app.exception.AuthenticationException;

public interface AuthenticationService {

    /**
     * Возвращает текущего аутентифицированного пользователя из контекста безопасности.
     *
     * @return объект {@link UserDetailsDto}, содержащий имя текущего пользователя.
     *
     * @throws AuthenticationException если пользователь не аутентифицирован или в JWT отсутствует имя пользователя.
     */
    UserDetailsDto getAuthenticatedUser();

    /**
     * Аутентифицирует существующего в системе пользователя.
     *
     * @param loginRq объект, содержащий данные для аутентификации (логин и пароль пользователя).
     * @return {@link TokenRs} объект, содержащий данные для доступа (accessToken и refreshToken).
     */
    TokenRs authenticateUser(LoginRq loginRq);

}

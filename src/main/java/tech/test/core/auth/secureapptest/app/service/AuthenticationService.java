package tech.test.core.auth.secureapptest.app.service;

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

}

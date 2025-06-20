package tech.test.core.auth.secureapptest.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import tech.test.core.auth.secureapptest.app.dto.UserDetailsDto;
import tech.test.core.auth.secureapptest.app.exception.AuthenticationException;
import tech.test.core.auth.secureapptest.app.service.AuthenticationService;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public UserDetailsDto getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("Пользователь не аутентифицирован");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Jwt jwt) {
            String username = jwt.getClaimAsString("preferred_username");

            if (username == null || username.isBlank()) {
                throw new AuthenticationException("Username не найден в jwt");
            }

            return new UserDetailsDto(username);
        }

        throw new AuthenticationException("Невозможно получить данные об аутентификации");
    }

}

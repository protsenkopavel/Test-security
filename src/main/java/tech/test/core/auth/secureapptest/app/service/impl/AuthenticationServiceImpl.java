package tech.test.core.auth.secureapptest.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import tech.test.core.auth.secureapptest.app.dto.LoginRq;
import tech.test.core.auth.secureapptest.app.dto.TokenRs;
import tech.test.core.auth.secureapptest.app.dto.UserDetailsDto;
import tech.test.core.auth.secureapptest.app.exception.AuthenticationException;
import tech.test.core.auth.secureapptest.app.service.AuthenticationService;
import tech.test.core.auth.secureapptest.app.service.OAuthService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final OAuthService oAuthService;

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

    @Override
    public TokenRs authenticateUser(LoginRq loginRq) {
        TokenRs tokenRs = oAuthService.authenticateUser(loginRq);
        log.info("Пользователь с логином {} успешно вошел в систему", loginRq.login());

        return tokenRs;
    }

}

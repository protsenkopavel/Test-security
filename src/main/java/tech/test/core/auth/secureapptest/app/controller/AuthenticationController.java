package tech.test.core.auth.secureapptest.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.test.core.auth.secureapptest.app.dto.LoginRq;
import tech.test.core.auth.secureapptest.app.dto.TokenRs;
import tech.test.core.auth.secureapptest.app.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public TokenRs authenticateUser(@RequestBody @Valid LoginRq loginRq) {
        return authenticationService.authenticateUser(loginRq);
    }

}

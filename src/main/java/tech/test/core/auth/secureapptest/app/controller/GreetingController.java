package tech.test.core.auth.secureapptest.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.test.core.auth.secureapptest.app.dto.UserDetailsDto;
import tech.test.core.auth.secureapptest.app.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/greeting")
@RequiredArgsConstructor
public class GreetingController {

    private final AuthenticationService authenticationService;

    @GetMapping("/hello")
    public ResponseEntity<String> greetUser() {
        UserDetailsDto authenticatedUserDetails = authenticationService.getAuthenticatedUser();

        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Hello, %s!".formatted(authenticatedUserDetails.username()));
    }

}

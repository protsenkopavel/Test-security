package tech.test.core.auth.secureapptest.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greeting")
public class GreetingController {

    @GetMapping("/hello")
    public ResponseEntity<?> greetUser() {
        String userName = "hello";

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userName);
    }

}

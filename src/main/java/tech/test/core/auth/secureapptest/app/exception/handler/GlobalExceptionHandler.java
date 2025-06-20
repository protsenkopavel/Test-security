package tech.test.core.auth.secureapptest.app.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.test.core.auth.secureapptest.app.exception.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleEntityNotFoundException(AuthenticationException ex) {
        String errorReason = "Ошибка аутентификации";

        log.warn("{}: {}", errorReason, ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        errors.put(errorReason, ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

}

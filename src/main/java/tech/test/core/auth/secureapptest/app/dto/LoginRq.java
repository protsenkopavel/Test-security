package tech.test.core.auth.secureapptest.app.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LoginRq(
        @NotNull(message = "Логин не может быть пустым.")
        @Length(min = 1, max = 50, message = "Длина логина должна составлять от 1 до 50 символов.")
        String login,
        @NotNull(message = "Пароль не может быть пустым.")
        @Length(min = 1, max = 50, message = "Длина пароля должна составлять от 1 до 50 символов.")
        String password
) {
}

# Тестовое задание для Java разработчика:
 - Развертывание Keycloak и Spring Boot приложения с OAuth2-авторизацией
## Цель задания:
 - Проверить навыки работы с Docker, Keycloak и Spring Security (OAuth2).
## Задача:
1. Развернуть Keycloak в Docker
- Использовать официальный образ quay.io/keycloak/keycloak.
- Настроить:
- Realm (например, test-realm).
- Клиент (spring-boot-app).
- Пользователя (test-user с паролем).
2. Развернуть Spring Boot приложение в Docker
- Приложение должно подключаться к Keycloak для аутентификации.
- Использовать Spring Security + OAuth2 (Keycloak Adapter или spring-boot-starter-oauth2-resource-server).
- Достаточно одной защищённой endpoint (например, /hello, возвращающей "Hello, {username}!").
3. Дополнительно:
◦ Написать docker-compose.yml для одновременного запуска Keycloak и Spring Boot.

## Требования к реализации:
- В README.md — инструкция по запуску.
- Приложение должно быть полностью рабочим: после входа по логину/паролю из Keycloak — доступ к защищённому эндпоинту.
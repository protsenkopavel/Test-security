### 1. Клонирование репозитория
```bash
git clone https://github.com/protsenkopavel/Test-security.git
cd Test-security/
```

### 2. Сборка приложения
```bash
docker-compose -f docker-compose.dev.yaml up -d --build
```

### 3. Проверка работоспособности
1. Доступ в админку keycloak: http://localhost:8080
- Логин/пароль: admin/admin

2. Аутентификация тестового пользователя:
- Выполнить запрос [`docs/requests/app-authentication.http`](./docs/requests/app-authentication.http) с dev окружением (Run with: dev).
- Либо с помощью curl:
  ```bash
  TOKEN=$(curl -s -X POST http://localhost:8081/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"login": "test-user", "password": "test"}' | jq -r .access_token)
  ```
- В случае успешной аутентификации access token автоматически запишется в переменную окружения.
- Если учетные данные введены неверно, ответ будет содержать сообщение "Ошибка аутентификации: неверное имя пользователя или пароль"
  со статусом 401 Unauthorized.
- Выполнить запрос [`docs/requests/test-api.http`](./docs/requests/test-api.http) с dev окружением (Run with: dev).
- Либо с помощью `curl`:
  ```bash
  curl http://localhost:8081/api/v1/greeting/hello -H "Authorization: Bearer $TOKEN"
  ```
- В случае успешного вызова в консоль будет записан ответ "Hello, test-user!" со статусом 200 ОК.
- Во всех остальных случаях статус ответа будет 401 Unauthorized.

### 4. Остановка приложения
```bash
docker-compose -f docker-compose.dev.yaml down
```
# Java UI + API autotests

Портфолио-проект автотестов на **Java 21**: UI на [Selenide](https://selenide.org/), API на [REST Assured](https://rest-assured.io/), отчёты [Allure](https://qameta.io/allure-report/), раннер [JUnit 5](https://junit.org/junit5/), CI на GitHub Actions.

Целевые приложения (публичные, без учётки):

- API: [JSONPlaceholder](https://jsonplaceholder.typicode.com/) — CRUD постов и чтение пользователя со схемой JSON
- UI: [the-internet.herokuapp.com](https://the-internet.herokuapp.com/) — логин, Add/Remove Elements, dropdown

## Стек

| Слой | Инструменты |
| --- | --- |
| Язык / сборка | Java 21, Gradle 8 |
| API | REST Assured, JSON Schema Validator, Jackson records |
| UI | Selenide 7, Page Object |
| Ассерты | AssertJ |
| Конфиг | Owner (`config.properties` + system properties) |
| Отчётность | Allure (JUnit 5, REST Assured filter, Selenide listener, скриншоты) |
| CI | GitHub Actions: отдельные джобы `api` и `ui` |

## Структура

```
src/test/java/com/amidosha/autotests/
  api/models|specs|tests   — модели, Request/Response spec, API-кейсы
  ui/pages|tests           — Page Object и UI-кейсы
  config                   — Owner-конфиг
  helpers                  — вложения Allure
src/test/resources/
  config.properties
  schemas/                 — JSON Schema для API
.github/workflows/tests.yml
```

## Как запустить

Нужны JDK 21 и Chrome (для UI).

```bash
# все тесты, UI в headless Chrome
./gradlew test -Dselenide.headless=true

# только API
./gradlew apiTest

# только UI
./gradlew uiTest -Dselenide.headless=true
```

Переопределение URL и браузера:

```bash
./gradlew apiTest -Dapi.baseUrl=https://jsonplaceholder.typicode.com
./gradlew uiTest -Dui.baseUrl=https://the-internet.herokuapp.com -Dbrowser=chrome
```

Результаты Allure пишутся в `build/allure-results`. Локальный HTML-отчёт:

```bash
allure serve build/allure-results
```

## CI

На каждый push и pull request в `main` GitHub Actions поднимает JDK 21 и гоняет API и UI отдельно. Allure-results и при падении UI — отчёты Gradle — уходят в artifacts. Запуск вручную: Actions → Tests → Run workflow, можно выбрать suite `all` / `api` / `ui`.

## Что покрыто

**API**

- GET `/posts/1` + JSON Schema
- GET `/posts` — непустая коллекция
- POST `/posts` — создание и echo полей
- PUT `/posts/1` — обновление
- DELETE `/posts/1`
- GET `/users/1` — вложенный `address` + schema

**UI**

- Успешный логин `tomsmith` / `SuperSecretPassword!`
- Невалидный пароль
- Добавление и удаление элементов
- Выбор option в dropdown

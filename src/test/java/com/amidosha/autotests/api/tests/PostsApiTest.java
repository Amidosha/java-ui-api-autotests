package com.amidosha.autotests.api.tests;

import com.amidosha.autotests.api.asserts.PostAsserts;
import com.amidosha.autotests.api.asserts.UserAsserts;
import com.amidosha.autotests.api.models.Post;
import com.amidosha.autotests.api.models.User;
import com.amidosha.autotests.api.specs.ApiSpecs;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Epic("JSONPlaceholder")
@Feature("API постов и пользователей")
@Owner("Amidosha")
@Tag("api")
class PostsApiTest {

    @Test
    @Story("Получение поста")
    @DisplayName("GET /posts/1 возвращает пост с валидной схемой и заполненными полями")
    void getPostByIdMatchesSchema() {
        Post post = step("Запрашиваем пост №1", () ->
                given(ApiSpecs.request())
                        .when()
                        .get("/posts/1")
                        .then()
                        .spec(ApiSpecs.success())
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/post.json"))
                        .extract().as(Post.class)
        );

        PostAsserts.assertRequiredFields(post, 1);
    }

    @Test
    @Story("Получение списка постов")
    @DisplayName("GET /posts возвращает непустой список постов")
    void getPostsReturnsCollection() {
        List<Post> posts = step("Запрашиваем список всех постов", () ->
                given(ApiSpecs.request())
                        .when()
                        .get("/posts")
                        .then()
                        .spec(ApiSpecs.success())
                        .extract().jsonPath().getList(".", Post.class)
        );

        PostAsserts.assertListIsNotEmpty(posts);
    }

    @Test
    @Story("Создание поста")
    @DisplayName("POST /posts создаёт пост и возвращает сгенерированный id")
    void createPostReturnsId() {
        Post payload = new Post(1, null, "portfolio title", "portfolio body");

        Post created = step("Создаём новый пост", () ->
                given(ApiSpecs.request())
                        .body(payload)
                        .when()
                        .post("/posts")
                        .then()
                        .spec(ApiSpecs.success())
                        .extract().as(Post.class)
        );

        PostAsserts.assertCreatedFromPayload(created, payload);
    }

    @Test
    @Story("Обновление поста")
    @DisplayName("PUT /posts/1 обновляет заголовок и текст поста")
    void updatePost() {
        Map<String, Object> payload = Map.of(
                "id", 1,
                "userId", 1,
                "title", "updated title",
                "body", "updated body"
        );

        Post updated = step("Обновляем пост №1", () ->
                given(ApiSpecs.request())
                        .body(payload)
                        .when()
                        .put("/posts/1")
                        .then()
                        .spec(ApiSpecs.success())
                        .extract().as(Post.class)
        );

        PostAsserts.assertUpdated(updated, 1, "updated title", "updated body");
    }

    @Test
    @Story("Удаление поста")
    @DisplayName("DELETE /posts/1 успешно удаляет пост")
    void deletePost() {
        step("Удаляем пост №1", () ->
                given(ApiSpecs.request())
                        .when()
                        .delete("/posts/1")
                        .then()
                        .spec(ApiSpecs.noContent())
        );
    }

    @Test
    @Story("Получение пользователя")
    @DisplayName("GET /users/1 возвращает пользователя с вложенным адресом")
    void getUserHasAddress() {
        User user = step("Запрашиваем пользователя №1", () ->
                given(ApiSpecs.request())
                        .when()
                        .get("/users/1")
                        .then()
                        .spec(ApiSpecs.success())
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user.json"))
                        .extract().as(User.class)
        );

        UserAsserts.assertHasEmailAndAddress(user, 1);
    }
}

package com.amidosha.autotests.api.asserts;

import com.amidosha.autotests.api.models.Post;
import io.qameta.allure.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public final class PostAsserts {

    private PostAsserts() {
    }

    @Step("Проверяем, что пост #{expectedId} заполнен обязательными полями")
    public static void assertRequiredFields(Post post, int expectedId) {
        assertThat(post.id()).isEqualTo(expectedId);
        assertThat(post.userId()).isPositive();
        assertThat(post.title()).isNotBlank();
        assertThat(post.body()).isNotBlank();
    }

    @Step("Проверяем, что список постов не пустой и у первого поста есть id")
    public static void assertListIsNotEmpty(List<Post> posts) {
        assertThat(posts).isNotEmpty();
        assertThat(posts.getFirst().id()).isNotNull();
    }

    @Step("Проверяем, что созданный пост содержит id и совпадает с отправленными данными")
    public static void assertCreatedFromPayload(Post created, Post payload) {
        assertThat(created.id()).isNotNull();
        assertThat(created.title()).isEqualTo(payload.title());
        assertThat(created.body()).isEqualTo(payload.body());
        assertThat(created.userId()).isEqualTo(payload.userId());
    }

    @Step("Проверяем, что пост #{expectedId} обновлён: title={expectedTitle}, body={expectedBody}")
    public static void assertUpdated(Post updated, int expectedId, String expectedTitle, String expectedBody) {
        assertThat(updated.id()).isEqualTo(expectedId);
        assertThat(updated.title()).isEqualTo(expectedTitle);
        assertThat(updated.body()).isEqualTo(expectedBody);
    }
}

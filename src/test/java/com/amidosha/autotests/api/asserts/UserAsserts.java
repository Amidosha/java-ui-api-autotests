package com.amidosha.autotests.api.asserts;

import com.amidosha.autotests.api.models.User;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public final class UserAsserts {

    private UserAsserts() {
    }

    @Step("Проверяем, что пользователь #{expectedId} содержит email и адрес с городом")
    public static void assertHasEmailAndAddress(User user, int expectedId) {
        assertThat(user.id()).isEqualTo(expectedId);
        assertThat(user.email()).contains("@");
        assertThat(user.address()).isNotNull();
        assertThat(user.address().city()).isNotBlank();
    }
}

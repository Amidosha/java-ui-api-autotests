package com.amidosha.autotests.ui.tests;

import com.amidosha.autotests.ui.pages.AddRemoveElementsPage;
import com.amidosha.autotests.ui.pages.DropdownPage;
import com.amidosha.autotests.ui.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("the-internet.herokuapp.com")
@Feature("Основные UI-сценарии")
@Owner("Amidosha")
@Tag("ui")
class TheInternetUiTest extends UiTestBase {

    @Test
    @Story("Авторизация")
    @DisplayName("Успешный вход показывает сообщение о защищённой зоне")
    void successfulLogin() {
        new LoginPage()
                .openPage()
                .login("tomsmith", "SuperSecretPassword!")
                .shouldShowSuccessfulLoginMessage();
    }

    @Test
    @Story("Авторизация")
    @DisplayName("Неверный пароль показывает сообщение об ошибке")
    void failedLogin() {
        new LoginPage()
                .openPage()
                .login("tomsmith", "wrong-password")
                .shouldShowInvalidPasswordMessage();
    }

    @Test
    @Story("Добавление и удаление элементов")
    @DisplayName("Добавленные элементы можно удалить по одному")
    void addAndRemoveElements() {
        new AddRemoveElementsPage()
                .openPage()
                .addElements(2)
                .shouldHaveDeleteButtons(2)
                .removeOne()
                .shouldHaveDeleteButtons(1);
    }

    @Test
    @Story("Выпадающий список")
    @DisplayName("В выпадающем списке можно выбрать Option 2")
    void selectDropdownOption() {
        new DropdownPage()
                .openPage()
                .selectByValue("2")
                .shouldHaveSelectedOption("Option 2");
    }
}

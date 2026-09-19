package com.amidosha.autotests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement username = $("#username");
    private final SelenideElement password = $("#password");
    private final SelenideElement submit = $("button[type='submit']");
    private final SelenideElement flash = $("#flash");

    @Step("Открываем страницу авторизации")
    public LoginPage openPage() {
        open("/login");
        username.shouldBe(visible);
        return this;
    }

    @Step("Входим под пользователем {user}")
    public LoginPage login(String user, String pass) {
        username.setValue(user);
        password.setValue(pass);
        submit.click();
        return this;
    }

    @Step("Проверяем сообщение об успешном входе")
    public LoginPage shouldShowSuccessfulLoginMessage() {
        flash.shouldBe(visible).shouldHave(text("You logged into a secure area!"));
        return this;
    }

    @Step("Проверяем сообщение об ошибке неверного пароля")
    public LoginPage shouldShowInvalidPasswordMessage() {
        flash.shouldBe(visible).shouldHave(text("Your password is invalid!"));
        return this;
    }
}

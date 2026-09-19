package com.amidosha.autotests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DropdownPage {

    private final SelenideElement dropdown = $("#dropdown");

    @Step("Открываем страницу Dropdown")
    public DropdownPage openPage() {
        open("/dropdown");
        dropdown.shouldBe(visible);
        return this;
    }

    @Step("Выбираем опцию со значением {value}")
    public DropdownPage selectByValue(String value) {
        dropdown.selectOptionByValue(value);
        return this;
    }

    @Step("Проверяем, что выбрана опция {optionText}")
    public DropdownPage shouldHaveSelectedOption(String optionText) {
        dropdown.getSelectedOption().shouldHave(exactText(optionText));
        return this;
    }
}

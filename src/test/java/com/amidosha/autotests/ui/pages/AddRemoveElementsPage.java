package com.amidosha.autotests.ui.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class AddRemoveElementsPage {

    private final SelenideElement addButton = $("button[onclick='addElement()']");
    private final ElementsCollection deleteButtons = $$("#elements button.added-manually");

    @Step("Открываем страницу Add/Remove Elements")
    public AddRemoveElementsPage openPage() {
        open("/add_remove_elements/");
        addButton.shouldBe(visible);
        return this;
    }

    @Step("Добавляем {count} элемент(а)")
    public AddRemoveElementsPage addElements(int count) {
        for (int i = 0; i < count; i++) {
            addButton.click();
        }
        return this;
    }

    @Step("Удаляем один элемент")
    public AddRemoveElementsPage removeOne() {
        deleteButtons.first().click();
        return this;
    }

    @Step("Проверяем, что на странице {count} кнопок Delete")
    public AddRemoveElementsPage shouldHaveDeleteButtons(int count) {
        deleteButtons.shouldHave(CollectionCondition.size(count));
        return this;
    }
}

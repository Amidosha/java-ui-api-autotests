package com.amidosha.autotests.helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public final class Attach {

    private Attach() {
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public static String pageSource() {
        return getWebDriver().getPageSource();
    }

    @Attachment(value = "Browser console logs", type = "text/plain")
    public static String browserConsoleLogs(String logs) {
        return logs;
    }
}

package com.amidosha.autotests.ui.tests;

import com.amidosha.autotests.config.Configs;
import com.amidosha.autotests.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public abstract class UiTestBase {

    @BeforeAll
    static void configureBrowser() {
        var config = Configs.config();
        Configuration.baseUrl = config.uiBaseUrl();
        Configuration.browser = System.getProperty("browser", config.browser());
        Configuration.browserSize = config.browserSize();
        Configuration.timeout = config.timeout();
        Configuration.pageLoadTimeout = config.pageLoadTimeout();
        Configuration.headless = Boolean.parseBoolean(
                System.getProperty("selenide.headless", String.valueOf(config.headless()))
        );
        Configuration.pageLoadStrategy = "eager";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("goog:loggingPrefs", Map.of(LogType.BROWSER, Level.ALL));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true)
        );
    }

    @BeforeEach
    void resetDriver() {
        closeWebDriver();
    }

    @AfterEach
    void addAttachments() {
        if (hasWebDriverStarted()) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            closeWebDriver();
        }
    }
}

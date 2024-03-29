package ru.banki.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.banki.config.ConfigReader;
import ru.banki.config.ProjectConfigurator;
import ru.banki.config.web.WebConfig;

import static ru.banki.ultils.AttachUtils.*;

public class TestBase {

    private static final WebConfig webConfig = ConfigReader.Instance.read();

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        ProjectConfigurator projectConfigurator = new ProjectConfigurator(webConfig);
        projectConfigurator.setWebConfig();
    }

    @AfterEach
    void attachments() {
        attachScreenshotAs("Last Step Screenshot");
        attachPageSource();
        attachBrowserConsoleLogs();
        attachVideo();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }
}
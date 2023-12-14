package ru.banki.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static ru.banki.ultils.AttachUtils.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void attachments() {
        attachScreenshotAs("Last Step Screenshot");
        attachPageSource();
        attachBrowserConsoleLogs();
        attachVideo();
        closeWebDriver();
    }
}

package ru.banki.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.banki.pages.MainPage;
import ru.banki.ultils.Utils;

import static ru.banki.data.HeaderNavigationTestsData.*;

public class HeaderNavigationTests extends TestBase {

    MainPage mainPage = new MainPage();
    Utils utils = new Utils();

    @Test
    @Tag("regress")
    @DisplayName("Header Navigation Test")
    public void headerNavigationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        mainPage.openPage()
                .hoverHeaderTab(headerTabDeposits)
                .headerSubmenuIsVisible()
                .clickHeaderSubmenuLink(headerLinkDeposits);
        utils.currentUrlEquals(depositPageUrl);
    }
}

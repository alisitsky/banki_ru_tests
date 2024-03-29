package ru.banki.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.banki.pages.MainPage;

import static io.qameta.allure.Allure.step;
import static ru.banki.data.HeaderTestsData.*;
import static ru.banki.ultils.UrlUtils.currentUrlIs;

@DisplayName("Examples of other statuses")
public class OtherStatusesExampleTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @Tag("regress")
    @DisplayName("Hover a tab and click a link [FAILED EXAMPLE]")
    public void failedTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });

        step("Header's submenu appears by hover", () -> {
            mainPage.hoverHeaderTab(headerTabDeposits)
                    .headerSubmenuIsVisible();
        });

        step("Click in \"" + headerLinkSpecials + "\" link and check URL", () -> {
            mainPage.clickHeaderSubmenuLink(headerLinkSpecials);
            currentUrlIs(specialsPageWrongUrl);
        });
    }

    @Test
    @Tag("regress")
    @Tag("skipped")
    @DisplayName("Hover a tab and click a link [SKIPPED EXAMPLE]")
    @Disabled("ticketid-1234")
    public void skippedTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
    }

    @Test
    @Tag("regress")
    @Tag("broken")
    @DisplayName("Exception has been thrown [BROKEN EXAMPLE]")
    void brokenTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        throw new RuntimeException("This test is intentionally broken");
    }
}

package ru.banki.tests;

import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.banki.pages.MainPage;

import static io.qameta.allure.Allure.step;
import static ru.banki.data.HeaderTestsData.*;
import static ru.banki.ultils.AttachUtils.attachScreenshotAs;
import static ru.banki.ultils.RandomUtils.currentUrlEquals;

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
            currentUrlEquals(specialsPageWrongUrl);
        });
    }

    @Test
    @Tag("regress")
    @Tag("skipped")
    @DisplayName("Hover a tab and click a link [SKIPPED EXAMPLE]")
    @Disabled("ticketid-1234")  //  todo: add ticket from jira when integrated
    public void skippedTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
    }

    @Test
    @Tag("regress")
    @Tag("broken")
    @DisplayName("Exception has been thrown [BROKEN EXAMPLE]")
    @AllureId("12345")
        //  todo: add ticket from jira when integrated, and maybe link to allure test ops
    void brokenTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        throw new RuntimeException("This test is intentionally broken");
    }
}

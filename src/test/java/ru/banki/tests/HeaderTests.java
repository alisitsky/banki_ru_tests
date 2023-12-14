package ru.banki.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.banki.pages.MainPage;
import ru.banki.pages.SearchResultPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;
import static ru.banki.data.HeaderTestsData.*;
import static ru.banki.ultils.AttachUtils.attachScreenshotAs;
import static ru.banki.ultils.RandomUtils.currentUrlEquals;

@DisplayName("Header Tests")
public class HeaderTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchResultPage searchResultPage = new SearchResultPage();

    @Test
    @Tag("regress")
    @DisplayName("Hover a tab and click a link")
    public void headerNavigationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
                mainPage.openPage();
                attachScreenshotAs("Step screenshot");
        });

        step("Header's submenu appears by hover", () -> {
                mainPage.hoverHeaderTab(headerTabDeposits)
                        .headerSubmenuIsVisible();
                attachScreenshotAs("Step screenshot");
        });

        step("Click in \"" + headerLinkSpecials + "\" link and check URL", () -> {
                mainPage.clickHeaderSubmenuLink(headerLinkSpecials);
                currentUrlEquals(specialsPageUrl);
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Header Search Test")
    public void headerSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
                mainPage.openPage();
                attachScreenshotAs("Step screenshot");
        });

        step("Click search-button and set value", () -> {
                mainPage.clickHeaderSearchButton()
                        .setSearchRequest(searchRequest);
        });

        step("Click submit button", () ->
                mainPage.clickSubmitSearchButton());

        step("Check url and 1st search result", () -> {
                searchResultPage.currentUrlIs(searchResultPageUrl)
                        .firstResultHasText(searchRequest);
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Location Change Test")
    public void LocationChangeTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String city = "Абакан"; //  todo написать метод, который берет рандомный город - вытаскивать список элементов и брать рандомный из них

        open("https://banki.ru");
        $$("button[type=button]").findBy(text("Другой город")).click();
        $$("div ul li[data-selected=false]").findBy(text(city)).click();
        sleep(2000);
        $("span[data-geo-selector]").shouldBe(visible).click();
        $("div.Area__sc-16fin9f-0 span").shouldHave(text(city));
    }
}

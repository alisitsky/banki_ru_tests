package ru.banki.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.banki.pages.MainPage;
import ru.banki.pages.SearchPage;

import static io.qameta.allure.Allure.step;
import static ru.banki.data.HeaderTestsData.*;
import static ru.banki.ultils.AttachUtils.attachScreenshotAs;
import static ru.banki.ultils.RandomUtils.currentUrlEquals;
import static ru.banki.ultils.RandomUtils.currentUrlIs;

@DisplayName("Header Tests")
public class HeaderTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    Faker faker = new Faker();

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
            attachScreenshotAs("Step screenshot");
        });

        step("Click submit button", () ->
            mainPage.clickSubmitSearchButton());

        step("Check url and 1st search result", () -> {
            currentUrlIs(searchResultPageUrl);
            searchPage.firstResultHasText(searchRequest);
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Location Change Test")
    public void LocationChangeTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        String randomCity = cities[faker.number().numberBetween(0, cities.length)];

        step("Open main page", () -> {
            mainPage.openPage();
            attachScreenshotAs("Step screenshot");
        });

        step("Click \"Другой город\"", () -> {
            mainPage.clickAnotherCityButton();
            attachScreenshotAs("Step screenshot");
        });

        step("Click random city from the list", () ->
            mainPage.clickCity(randomCity)
        );

        step("Check new city is applied", () -> {
            mainPage.clickGeoSelectorButton()
                    .currentCityNameIs(randomCity);
        });
    }
}
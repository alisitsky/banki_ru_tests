package ru.banki.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.banki.pages.MainPage;
import ru.banki.pages.SearchPage;

import static io.qameta.allure.Allure.step;
import static ru.banki.data.HeaderTestsData.*;
import static ru.banki.ultils.UrlUtils.currentUrlIs;

@DisplayName("Header Tests")
public class HeaderTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    Faker faker = new Faker();

    @Test
    @Tag("header_test")
    @DisplayName("Hover a tab and click a link")
    public void headerNavigationTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });

        step("Header's submenu appears by hover", () -> {
            mainPage.hoverHeaderTab(headerTabDeposits)
                    .headerSubmenuIsVisible();
        });

        step("Click in \"" + headerLinkSpecials + "\" link and check URL", () -> {
            mainPage.clickHeaderSubmenuLink(headerLinkSpecials);
            currentUrlIs(specialsPageUrl);
        });
    }

    @Test
    @Tag("header_test")
    @DisplayName("Header Search Test")
    public void headerSearchTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });

        step("Click search-button and set value", () -> {
            mainPage.clickHeaderSearchButton()
                    .setSearchRequest(searchRequest);
        });

        step("Click submit button", () ->
                mainPage.clickSubmitSearchButton());

        step("Check url and 1st search result", () -> {
            currentUrlIs(searchResultPageUrl);
            searchPage.firstResultHasText(searchRequest);
        });
    }

    @Test
    @Tag("header_test")
    @DisplayName("Location Change Test")
    public void LocationChangeTest() {
        String randomCity = cities[faker.number().numberBetween(0, cities.length)];

        step("Open main page", () -> {
            mainPage.openPage();
        });

        step("Click \"Другой город\" button", () -> {
            mainPage.clickAnotherCityButton();
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
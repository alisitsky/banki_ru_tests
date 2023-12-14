package ru.banki.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import ru.banki.ultils.RandomUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    RandomUtils randomUtils = new RandomUtils();
    Faker faker = new Faker();

    SelenideElement
            widgetSearchBanksInput = $("div[data-test='widget-search-banks_input'] input[type=text]"),
            tabDeposits = $$("ul.main-menu__sections li").findBy(text("Вклады")),
            mainPageBody = $("main.page-container__body"),
            headerSubmenu = $("div.main-menu__submenu"),
            headerSearchButton = $("div.header-search"),
            searchInput = $("input[type=search]"),
            submitSearchButton = $("button[type=submit]"),
            anotherCityButton = $$("button[type=button]").findBy(text("Другой город")),
            currentCityName = $("div[class^=Area]"),
            geoSelectorButton = $("span[data-geo-selector]");

    ElementsCollection
            headerSubmenuLinks = $$("div.main-menu__submenu-item a"),
            citiesList = $$("div ul li[data-selected=false]");



    public MainPage openPage() {
        open("https://banki.ru");
        mainPageBody.should(appear);
        return this;
    }

    public MainPage scrollUntilElementVisible(SelenideElement selenideElement) {
        randomUtils.scrollUntilElementAppears(selenideElement);
        return this;
    }

    public MainPage hoverHeaderTab(String tabName) {
        $$("ul.main-menu__sections li").findBy(text(tabName)).hover();
        return this;
    }

    public MainPage headerSubmenuIsVisible() {
        headerSubmenu.shouldBe(visible);
        return this;
    }

    public MainPage clickHeaderSubmenuLink(String linkText) {
        headerSubmenuLinks.findBy(text(linkText)).click();
        return this;
    }
   public MainPage clickHeaderSearchButton() {
       headerSearchButton.click();
       return this;
    }

    public MainPage setSearchRequest(String text) {
        searchInput.setValue(text);
        return this;
    }

    public MainPage clickSubmitSearchButton() {
        submitSearchButton.click();
        return this;
    }

    public MainPage clickCity(String city) {
        citiesList.findBy(text(city)).click();
        mainPageBody.should(appear);    //
        return this;
    }

    public MainPage clickAnotherCityButton() {
        anotherCityButton.click();
        return this;
    }

    public MainPage currentCityNameIs(String cityName) {
        currentCityName.shouldHave(text(cityName));
        return this;
    }

    public MainPage clickGeoSelectorButton() {
        geoSelectorButton.shouldBe(visible, Duration.ofSeconds(4))
                .shouldBe(interactable, Duration.ofSeconds(4))
                .click();
        return this;
    }

    public MainPage aaa() {

        return this;
    }

}
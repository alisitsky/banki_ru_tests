package ru.banki.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.banki.ultils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    RandomUtils randomUtils = new RandomUtils();

    SelenideElement
            widgetSearchBanksInput = $("div[data-test='widget-search-banks_input'] input[type=text]"),
            tabDeposits = $$("ul.main-menu__sections li").findBy(text("Вклады")),
            headerSubmenu = $("div.main-menu__submenu"),
            headerSearchButton = $("div.header-search"),
            searchInput = $("input[type=search]"),
            submitSearchButton = $("button[type=submit]");

    ElementsCollection
            headerSubmenuLinks = $$("div.main-menu__submenu-item a");



    public MainPage openPage() {
        open("https://banki.ru");
        $("main.page-container__body").should(appear);
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

    public MainPage aaa() {

        return this;
    }

}
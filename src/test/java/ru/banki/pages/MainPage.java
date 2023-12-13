package ru.banki.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.banki.ultils.Utils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    Utils utils = new Utils();

    SelenideElement
            widgetSearchBanksInput = $("div[data-test='widget-search-banks_input'] input[type=text]"),
            tabDeposits = $$("ul.main-menu__sections li").findBy(text("Вклады")),
            headerSubmenu = $("div.main-menu__submenu");

    ElementsCollection
            headerSubmenuLinks = $$("div.main-menu__submenu-item a");



    public MainPage openPage() {
        open("https://banki.ru");
        $("main.page-container__body").should(appear);
        return this;
    }

    public MainPage scrollUntilElementVisible(SelenideElement selenideElement) {
        utils.scrollUntilElementAppears(selenideElement);
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
   public MainPage aaa() {

        return this;
    }

}
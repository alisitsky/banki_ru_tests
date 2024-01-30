package ru.banki.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.banki.ultils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static ru.banki.ultils.RandomUtils.clickUntilAppears;
import static ru.banki.data.OnlineCalculatorTestsData.calcValueBeforeChange;
import static ru.banki.ultils.RandomUtils.getRandomInt;

public class MainPage {

    RandomUtils randomUtils = new RandomUtils();

    SelenideElement
            mainPageBody = $("main.page-container__body"),
            headerSubmenu = $("div.main-menu__submenu"),
            headerSearchButton = $("div.header-search"),
            searchInput = $("input[type=search]"),
            submitSearchButton = $("button[type=submit]"),
            anotherCityButton = $$("button[type=button]").findBy(text("Другой город")),
            currentLocationPopup = $("div[class^=Area]"),
            geoSelectorButton = $("span[data-geo-selector]"),
            creditSumSlider = $("div[data-role=ranger]"),
            creditPeriodInput = $("div[data-test=credit-period] input"),
            creditTimeSelectButton = $("div[class^=InputWithSelect").$$("div[class^=InputWithSelect").get(1);
    ElementsCollection
            headerSubmenuLinks = $$("div.main-menu__submenu-item a"),
            citiesList = $$("div ul li[data-selected=false]"),
            creditTimeSelectOptions = $$("div[class^=DropdownList] li");

    public MainPage openPage() {
        open(baseUrl);
        mainPageBody.should(appear);
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

    public MainPage clickGeoSelectorButton() {
        clickUntilAppears(geoSelectorButton, currentLocationPopup, 4);
        return this;
    }

    public MainPage currentCityNameIs(String cityName) {
        currentLocationPopup.shouldHave(text(cityName));
        return this;
    }

    public MainPage clickSliderCenter() {
        creditSumSlider.click();
        return this;
    }

    public MainPage setRandomTimeValue() {
        creditPeriodInput.sendKeys(Keys.BACK_SPACE);
        creditPeriodInput.setValue(String.valueOf(getRandomInt(1, 99)));
        return this;
    }

    public MainPage setRandomTimeMeasurement() {
        creditTimeSelectButton.click();
        creditTimeSelectOptions.get(getRandomInt(0, 2)).click();
        return this;
    }

}
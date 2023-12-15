package ru.banki.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.banki.ultils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.banki.ultils.RandomUtils.clickUntilVisible;
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
            calcTabsPannel = $("div[data-test=tabs-panel]"),
            calcDepositsTab = $("div[data-test=tabs-panel-tab-deposits]"),
            calcCreditsTabContent = $("div[data-test=credits-home-page-widget]"),
            calcDepositsTabContent = $("div[data-test=deposits-home-page-widget]"),
            creditSumSlider = $("div[data-role=ranger]"),
            creditCalculatedValue = $("div[data-test=widget-tab-credit-calc-payment]"),
            creditPeriodInput = $("div[data-test=credit-period] input"),
            creditTimeSelectButton = $("div[class^=InputWithSelect").$$("div[class^=InputWithSelect").get(1),
            calcMortgageInsuranceTab = $("div[data-test=tabs-panel-tab-mortgage-insurance]"),
            calcBirthDateInput = $("input[data-testid=input-mask]"),
            calcSubmitButton = $("button[data-test=main-ins-tab-hypothec-calculate]");

    ElementsCollection
            headerSubmenuLinks = $$("div.main-menu__submenu-item a"),
            citiesList = $$("div ul li[data-selected=false]"),
            creditTimeSelectOptions = $$("div[class^=DropdownList] li");

    public MainPage openPage() {
        open("https://banki.ru");
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
        clickUntilVisible(geoSelectorButton, currentLocationPopup, 4);
        return this;
    }

    public MainPage currentCityNameIs(String cityName) {
        currentLocationPopup.shouldHave(text(cityName));
        return this;
    }

    public MainPage scrollToCalcWidget() {
        randomUtils.scrollUntilElementAppears(calcTabsPannel);
        return this;
    }

    public MainPage checkTabsVisibilityBeforeSwitch() {
        calcCreditsTabContent.shouldBe(visible);
        calcDepositsTabContent.shouldNotBe(visible);
        return this;
    }

    public MainPage switchCalcTab() {
        calcDepositsTab.click();
        return this;
    }

    public MainPage checkTabsVisibilityAfterSwitch() {
        calcDepositsTabContent.shouldBe(visible);
        calcCreditsTabContent.shouldNotBe(visible);
        return this;
    }

    public MainPage saveCalculatedValue() {
        calcValueBeforeChange = $("div[data-test=widget-tab-credit-calc-payment]").text();
        return this;
    }

    public MainPage clickSliderCenter() {
        creditSumSlider.click();
        return this;
    }

    public MainPage calcValueIsChanged() {
        creditCalculatedValue.shouldNotHave(text(calcValueBeforeChange));

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

    public MainPage switchToCalcTab() {
        calcMortgageInsuranceTab.shouldBe(visible).click();
        return this;
    }

    public MainPage setBirthDate(String date) {
        calcBirthDateInput.shouldBe(visible).setValue(date);
        return this;
    }

    public MainPage clickSubmitCalcButton() {
        calcSubmitButton.click();
        return this;
    }
}
package ru.banki.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.banki.pages.MainPage;
import ru.banki.ultils.RandomUtils;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.WebDriverConditions.urlStartingWith;
import static io.qameta.allure.Allure.step;
import static ru.banki.data.OnlineCalculatorTestsData.calcValueBeforeChange;
import static ru.banki.ultils.AttachUtils.attachScreenshotAs;

@DisplayName("Online Calc Tests")
public class OnlineCalculatorTests extends TestBase {
    MainPage mainPage = new MainPage();
    RandomUtils randomUtils = new RandomUtils();

    @Test
    @Tag("regress")
    @DisplayName("Switch tab and check its visibility")
    public void tabsSwitchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            mainPage.openPage();
            attachScreenshotAs("Step screenshot");
        });

        step("Scroll to online-calc widget", () -> {
            mainPage.scrollToCalcWidget();
        });

        step("First tab is visible, \nsecond is not", () -> {
            mainPage.checkTabsVisibilityBeforeSwitch();
            attachScreenshotAs("Step screenshot");
        });

        step("Click second tab", () -> {
            mainPage.switchCalcTab();
        });

        step("Second tab is visible, \nfirst is not", () -> {
            mainPage.checkTabsVisibilityAfterSwitch();
        });
    }

    @Test
    @Tag("regress")
    @DisplayName("Calculated value changes when input changes")
    public void calcChangesValueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            mainPage.openPage();
            attachScreenshotAs("Step screenshot");
        });

        step("Scroll to online-calc widget", () -> {
            mainPage.scrollToCalcWidget();
            attachScreenshotAs("Step screenshot");
        });

        step("Save calculated value", () -> {
            mainPage.saveCalculatedValue();
        });

        step("Click the slider", () -> {
            mainPage.clickSliderCenter();
        });

        step("Check value is changed", () -> {
            mainPage.calcValueIsChanged()
                    .saveCalculatedValue();
            attachScreenshotAs("Step screenshot");
        });

        step("Set random time period", () -> {
            mainPage.setRandomTimeValue();
        });

        step("Check value is changed", () -> {
            mainPage.calcValueIsChanged()
                    .saveCalculatedValue();
            attachScreenshotAs("Step screenshot");
        });

        step("Set random unit of measurement", () -> {
            mainPage.setRandomTimeMeasurement();
        });

        step("Check value is changed", () -> {
            mainPage.calcValueIsChanged();
        });
    }

    @Test
    public void urlParamsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            mainPage.openPage();
            attachScreenshotAs("Step screenshot");
        });

        step("Scroll to online-calc widget", () -> {
            mainPage.scrollToCalcWidget();
        });

        // клик в "страхование ипотеки"
        $("div[data-test=tabs-panel-tab-mortgage-insurance]").shouldBe(visible).click();

        // ввести рандомную дату в последнее поле
        $("input[data-testid=input-mask]").shouldBe(visible).setValue(randomUtils.getRandomBirthDateString());

//        // нажать на кнопку
        $("button[data-test=main-ins-tab-hypothec-calculate]").click();

        // Проверить урл
        webdriver().shouldHave(urlStartingWith("https://www.banki.ru/insurance/order/realty/mortgage/result"))
                .shouldHave(urlContaining("amount=3000000"));

    }
}
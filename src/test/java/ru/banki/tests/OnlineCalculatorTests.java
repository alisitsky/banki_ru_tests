package ru.banki.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.banki.pages.MainPage;

import static io.qameta.allure.Allure.step;
import static ru.banki.ultils.AttachUtils.attachScreenshotAs;
import static ru.banki.ultils.RandomUtils.getRandomBirthDateString;
import static ru.banki.ultils.RandomUtils.urlHasParam;

@DisplayName("Online Calc Tests")
public class OnlineCalculatorTests extends TestBase {
    MainPage mainPage = new MainPage();

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
    @Tag("regress")
    @DisplayName("Url params for Mortgage page are correct")
    public void urlParamsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        String randomBirthDate = getRandomBirthDateString();
        String urlParamBirthDate = "birthDate=" + randomBirthDate;

        step("Open main page", () -> {
            mainPage.openPage();
            attachScreenshotAs("Step screenshot");
        });

        step("Scroll to online-calc widget", () -> {
            mainPage.scrollToCalcWidget();
            attachScreenshotAs("Step screenshot");
        });

        step("Click Mortgage Insurance tab", () -> {
            mainPage.switchToCalcTab();
            attachScreenshotAs("Step screenshot");
        });

        step("Set random birth date", () -> {
            mainPage.setBirthDate(randomBirthDate);
            attachScreenshotAs("Step screenshot");
        });

        step("Click Submit button", () -> {
            mainPage.clickSubmitCalcButton();
        });

        step("Check url has parameter", () ->
            urlHasParam(urlParamBirthDate));

    }
}
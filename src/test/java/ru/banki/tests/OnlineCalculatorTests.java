package ru.banki.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.banki.pages.MainPage;

import static io.qameta.allure.Allure.step;
import static ru.banki.ultils.RandomUtils.getRandomBirthDateString;
import static ru.banki.ultils.RandomUtils.urlHasParam;

@DisplayName("Online Calc Tests")
public class OnlineCalculatorTests extends TestBase {
    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Switch tab and check its visibility")
    public void tabsSwitchTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });

        step("Scroll to online-calc widget", () -> {
            mainPage.scrollToCalcWidget();
        });

        step("First tab is visible, \nsecond is not", () -> {
            mainPage.checkTabsVisibilityBeforeSwitch();
        });

        step("Click second tab", () -> {
            mainPage.switchCalcTab();
        });

        step("Second tab is visible, \nfirst is not", () -> {
            mainPage.checkTabsVisibilityAfterSwitch();
        });
    }

    @Test
    @DisplayName("Calculated value changes when input changes")
    public void calcChangesValueTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });

        step("Scroll to online-calc widget", () -> {
            mainPage.scrollToCalcWidget();
        });

        step("Save calculated value", () -> {
            mainPage.saveCalculatedValue();
        });

        step("Click the slider", () ->
                mainPage.clickSliderCenter());

        step("Check value is changed", () -> {
            mainPage.calcValueIsChanged()
                    .saveCalculatedValue();
        });

        step("Set random time period", () ->
                mainPage.setRandomTimeValue());

        step("Check value is changed", () -> {
            mainPage.calcValueIsChanged()
                    .saveCalculatedValue();
        });

        step("Set random unit of measurement", () ->
                mainPage.setRandomTimeMeasurement());

        step("Check value is changed", () ->
                mainPage.calcValueIsChanged());
    }

    @Test
    @DisplayName("Generated url has parameter from calc input")
    public void urlHasParamFromInputTest() {
        String randomBirthDate = getRandomBirthDateString();
        String urlParamBirthDate = "birthDate=" + randomBirthDate;

        step("Open main page", () -> {
            mainPage.openPage();
        });

        step("Scroll to online-calc widget", () -> {
            mainPage.scrollToCalcWidget();
        });

        step("Click Mortgage Insurance tab", () -> {
            mainPage.switchToCalcTab();
        });

        step("Set random birth date", () -> {
            mainPage.setBirthDate(randomBirthDate);
        });

        step("Click Submit button", () ->
                mainPage.clickSubmitCalcButton());

        step("Check url has parameter", () ->
                urlHasParam(urlParamBirthDate));
    }
}


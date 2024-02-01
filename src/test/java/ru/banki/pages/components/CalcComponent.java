package ru.banki.pages.components;

import com.codeborne.selenide.SelenideElement;
import ru.banki.ultils.RandomUtils;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.banki.data.OnlineCalculatorTestsData.calcValueBeforeChange;

public class CalcComponent {

    RandomUtils randomUtils = new RandomUtils();

    SelenideElement
            calcTabsPannel = $("div[data-test=tabs-panel]"),
            calcDepositsTab = $("div[data-test=tabs-panel-tab-deposits]"),
            calcCreditsTabContent = $("div[data-test=credits-home-page-widget]"),
            calcDepositsTabContent = $("div[data-test=deposits-home-page-widget]"),
            creditCalculatedValue = $("div[data-test=widget-tab-credit-calc-payment]"),
            calcMortgageInsuranceTab = $("div[data-test=tabs-panel-tab-mortgage-insurance]"),
            calcBirthDateInput = $("div[data-test=mortgage-insurance-home-page-widget] input[data-testid=input-mask]"),
            calcSubmitButton = $("button[data-test=main-ins-tab-hypothec-calculate]");


    public CalcComponent scrollToCalcWidget() {
        randomUtils.scrollUntilElementLoads(calcTabsPannel);
        return this;
    }

    public CalcComponent checkTabsVisibilityBeforeSwitch() {
        calcCreditsTabContent.shouldBe(visible);
        calcDepositsTabContent.shouldNotBe(visible);
        return this;
    }

    public CalcComponent switchCalcTab() {
        calcDepositsTab.click();
        return this;
    }

    public CalcComponent checkTabsVisibilityAfterSwitch() {
        calcDepositsTabContent.shouldBe(visible);
        calcCreditsTabContent.shouldNotBe(visible);
        return this;
    }

    public CalcComponent saveCalculatedValue() {
        calcValueBeforeChange = $("div[data-test=widget-tab-credit-calc-payment]").text();
        return this;
    }

    public CalcComponent calcValueIsChanged() {
        creditCalculatedValue.shouldNotHave(text(calcValueBeforeChange));
        return this;
    }

    public CalcComponent switchToCalcTab() {
        calcMortgageInsuranceTab.shouldBe(visible).click();
        sleep(500);
        return this;
    }

    public CalcComponent setBirthDate(String date) {
        calcBirthDateInput.shouldBe(visible).setValue(date);
        return this;
    }

    public CalcComponent clickSubmitCalcButton() {
        calcSubmitButton.click();
        return this;
    }
}

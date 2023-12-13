package ru.banki.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.banki.pages.MainPage;
import ru.banki.ultils.Utils;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.WebDriverConditions.urlStartingWith;

public class OnlineCalculatorTests {
    MainPage mainPage = new MainPage();
    Utils utils = new Utils();

    @Test
    public void tabsSwitchTest() {
        open("https://banki.ru");

        // подскролл к табам
        mainPage.scrollUntilElementVisible($("div[data-test=tabs-panel]"));

        // проверить, что отображается содержимое таба Кредиты
        $("div[data-test=credits-home-page-widget]").shouldBe(visible);

        // проверить, что не отображается таб Вклады
        $("div[data-test=deposits-home-page-widget]").shouldNotBe(visible);

        // клик на таб Вклады
        $("div[data-test=tabs-panel-tab-deposits]").click();

        // проверить что не отображается таб Кредиты
        $("div[data-test=credits-home-page-widget]").shouldNotBe(visible);

        // отображаются вклады
        $("div[data-test=deposits-home-page-widget]").shouldBe(visible);
    }

    @Test
    public void calcChangesValueTest() {
        open("https://banki.ru");
        mainPage.scrollUntilElementVisible($("div[data-test=tabs-panel]"));

        // сохранить значение калькулятора
        String calcValueBeforeChange = $("div[data-test=widget-tab-credit-calc-payment]").text();

        // клик в ползунок
        $("div[data-role=ranger]").click(); //  ок!!!

        // проверить, что зн.кальк.изменилось и сохранить
        $("div[data-test=widget-tab-credit-calc-payment]").shouldNotHave(text(calcValueBeforeChange));
        calcValueBeforeChange = $("div[data-test=widget-tab-credit-calc-payment]").text();

        // поменять срок (на рандомный из диапазона)
        $("div[data-test=credit-period] input").sendKeys(Keys.BACK_SPACE);
        $("div[data-test=credit-period] input").setValue(String.valueOf(utils.getRandomInt(1, 99)));

        // проверить, что зн.кальк.изменилось
        $("div[data-test=widget-tab-credit-calc-payment]").shouldNotHave(text(calcValueBeforeChange));
        calcValueBeforeChange = $("div[data-test=widget-tab-credit-calc-payment]").text();

        // вызвать дропдаун
        $("div[class^=InputWithSelect").$$("div[class^=InputWithSelect").get(1).click();

        // клик в 1 или 2 значение дропдауна
        $$("div[class^=DropdownList] li").get(utils.getRandomInt(0, 2)).click();

        // проверить, что зн.кальк.изменилось
        $("div[data-test=widget-tab-credit-calc-payment]").shouldNotHave(text(calcValueBeforeChange));
    }

    @Test
    public void urlParamsTest() {
        open("https://banki.ru");
        mainPage.scrollUntilElementVisible($("div[data-test=tabs-panel]"));

        // клик в "страхование ипотеки"
        $("div[data-test=tabs-panel-tab-mortgage-insurance]").shouldBe(visible).click();

        // ввести рандомную дату в последнее поле   todo: написать метод в utils, который будет вставлять рандомную дату
        $("input[data-testid=input-mask]").shouldBe(visible).setValue(utils.getRandomBirthDateString());

//        // нажать на кнопку
        $("button[data-test=main-ins-tab-hypothec-calculate]").click();

        // Проверить урл
        webdriver().shouldHave(urlStartingWith("https://www.banki.ru/insurance/order/realty/mortgage/result"))
                    .shouldHave(urlContaining("amount=3000000"));

    }
}
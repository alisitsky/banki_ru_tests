package ru.banki.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.banki.ultils.RandomUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    RandomUtils randomUtils = new RandomUtils();

    SelenideElement
            widgetSearchBanksInput = $("div[data-test='widget-search-banks_input'] input[type=text]");

    public MainPage openPage() {
        open("https://banki.ru");
        $("main.page-container__body").should(appear);
        return this;
    }

    public MainPage scrollUntilBanksInputLoads () {
        randomUtils.scrollAndCheckElementVisibility(widgetSearchBanksInput);
        return this;
    }

    public MainPage setValueInBankSearchInput (String text) {
        widgetSearchBanksInput.scrollTo().click();
        widgetSearchBanksInput.setValue(text);
        return this;
    }
}
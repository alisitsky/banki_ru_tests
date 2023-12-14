package ru.banki.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.banki.ultils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class SearchResultPage {

    RandomUtils randomUtils = new RandomUtils();

    SelenideElement
            firstSearchItem = $$("li[data-test=search-item-result]").first();


    public SearchResultPage currentUrlIs(String url) {
        webdriver().shouldHave(url(url));
        return this;
    }

    public SearchResultPage firstResultHasText(String text) {
        firstSearchItem.shouldHave(text(text));
        return this;
    }


}
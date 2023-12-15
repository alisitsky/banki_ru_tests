package ru.banki.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    SelenideElement
            firstSearchItem = $$("li[data-test=search-item-result]").first();

    public SearchPage firstResultHasText(String text) {
        firstSearchItem.shouldHave(text(text));
        return this;
    }
}
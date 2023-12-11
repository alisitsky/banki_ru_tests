package ru.banki.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class HeaderSearchTests extends TestBase {

    @Test
    public void headerSearchTest() {
        open("https://banki.ru");
        $("div.header-search").click();
        $("input[type=search]").setValue("сбер");
        $("button[type=submit]").click();
        webdriver().shouldHave(url("https://www.banki.ru/search/?utf8=1&q=%D1%81%D0%B1%D0%B5%D1%80"));
        $$("li[data-test=search-item-result]").first().shouldHave(text("Сбербанк"));

    }
}

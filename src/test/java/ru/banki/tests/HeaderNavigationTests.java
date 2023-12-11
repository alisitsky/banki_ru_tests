package ru.banki.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class HeaderNavigationTests extends TestBase {

    @Test
    public void headerNavigationTest() {
        open("https://banki.ru");
        $$("ul.main-menu__sections li").findBy(text("Вклады")).hover();
        $("div.main-menu__submenu").shouldBe(visible);
        $$("div.main-menu__submenu-item a").findBy(text("Вклады")).click();
        webdriver().shouldHave(url("https://www.banki.ru/products/deposits/?source=submenu_deposits"));
    }
}

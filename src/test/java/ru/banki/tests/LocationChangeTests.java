package ru.banki.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LocationChangeTests {

    @Test
    public void LocationChangeTest() {
        String city = "Абакан";

        open("https://banki.ru");
        $$("button[type=button]").findBy(text("Другой город")).click();
        $$("div ul li[data-selected=false]").findBy(text(city)).click();
        sleep(2000);
        $("span[data-geo-selector]").shouldBe(visible).click();
        $("div.Area__sc-16fin9f-0 span").shouldHave(text(city));
    }
}

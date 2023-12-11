package ru.banki.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CarouselNavigationTests {

//    todo: сделать тикет на дорабтку, залинковать сюда, убрать комменты
//    todo: А ЛУЧШЕ ВЫПИЛИТЬ ЭТОТ ТЕСТ
    @Test
    @Tag("skipped")
    @Disabled("ticketid-1234")
    public void CarouselScrollTest() {
        open("https://banki.ru");
        $("a[data-gtm='main/products/credit-master']").scrollTo().isDisplayed();
        $("div[data-test=product-showcase] button[data-test=scroller-nav-next-button]").click();
        sleep(2000);    //  todo: избавиться от слипа (без него слишком рано кликает)
        $("div[data-test=product-showcase] button[data-test=scroller-nav-next-button]").click();
        $("a[data-gtm='main/products/credit-master']").shouldBe(hidden);   //  todo: первый эл.карусели всегда visible - как проверить, что скролл произошел =(
        $$("div[data-test=product-showcase-title]").findBy(text("Подбор кредита")).shouldBe(disabled);
        executeJavaScript("return arguments[0].offsetParent === null", $("a[data-gtm='main/products/credit-master']"));
    }
}

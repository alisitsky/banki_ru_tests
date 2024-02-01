package ru.banki.ultils;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class UrlUtils {

    public static void currentUrlIs(String url) {
        webdriver().shouldHave(url(url));
    }

    public static void urlHasParam(String param) {
        webdriver().shouldHave(urlContaining(param));
    }
}

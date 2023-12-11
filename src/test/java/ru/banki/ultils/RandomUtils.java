package ru.banki.ultils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.banki.pages.MainPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RandomUtils {

    public void scrollAndCheckElementVisibility(SelenideElement selenideElement) {
        while (!selenideElement.is(appear)) {
            if (canScrollDown()) {
                $("body").sendKeys(Keys.PAGE_DOWN);
                sleep(200);
            } else
                break;
        }
        selenideElement.shouldBe(visible);
    }

    private boolean canScrollDown() {
        return (boolean) executeJavaScript(
                "return document.documentElement.scrollTop + window.innerHeight < document.documentElement.scrollHeight"
        );
    }
}
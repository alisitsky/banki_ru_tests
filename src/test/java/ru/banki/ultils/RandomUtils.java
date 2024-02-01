package ru.banki.ultils;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RandomUtils {
    public void scrollUntilElementLoads(SelenideElement selenideElement) {
        while (!selenideElement.is(visible)) {
            if (canScrollDown()) {
                $("body").sendKeys(Keys.PAGE_DOWN);
                sleep(200);
            } else
                break;
        }
        $("body").sendKeys(Keys.PAGE_DOWN);
        selenideElement.scrollTo();
        sleep(300);
    }

    private boolean canScrollDown() throws NullPointerException{
        return executeJavaScript(
                "return document.documentElement.scrollTop + window.innerHeight < document.documentElement.scrollHeight"
        );
    }

    public static int getRandomInt(int from, int to){
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public static String getRandomBirthDateString(){
        Faker faker = new Faker();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.YEAR, -90);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.YEAR, -18);

        Date randomDate = faker.date().between(startCalendar.getTime(), endCalendar.getTime());
        return dateFormatter.format(randomDate);
    }

    public static void clickUntilAppears(SelenideElement elementToClick, SelenideElement elementToCheck, int secRepeat) {
        int i = secRepeat * 10;
        while (!elementToCheck.is(visible) && i > 0) {
            sleep(100);
            elementToClick.click();
            i--;
        }
    }
}
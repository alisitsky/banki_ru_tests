package ru.banki.tests;


import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.banki.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class WidgetSearchBanksTest {

    MainPage mainPage = new MainPage();

    @Test
    public void SearchAndClickSuggest() {
        mainPage.openPage()
                .scrollUntilBanksInputLoads()
                .setValueInBankSearchInput("cбер");

        sleep(2000);

        //  todo не появляется саджест. проверить гипотезу, что поможет sleep перед вводом - запустить по 10 раз с ним и без

    }
}

package ru.banki.tests;

import org.junit.jupiter.api.Test;
import ru.banki.ultils.Utils;

public class TestTest {

    @Test
    public void main()
    {
        Utils utils = new Utils();
        System.out.println(utils.getRandomInt(0, 2));
    }
}

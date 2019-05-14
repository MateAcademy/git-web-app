package utils;

import java.util.Random;

public class RandomHelper {

    public static String getRandomeCode() {
        Random random = new Random();
        String randomCode = String.valueOf(random.nextInt(8001) + 1000);
        return randomCode;
    }

}

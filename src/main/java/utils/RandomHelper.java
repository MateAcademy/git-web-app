package utils;

import java.util.Random;
import java.util.UUID;

public class RandomHelper {

    public static String getRandomeCode() {
        Random random = new Random();
        String randomCode = String.valueOf(random.nextInt(8001)+1000);
        return randomCode;
    }

}

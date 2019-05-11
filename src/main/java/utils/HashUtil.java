package utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.log4j.Logger;

public class HashUtil {

    private static Logger logger = Logger.getLogger(HashUtil.class);

    public static String getSHA512SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            logger.error("Can't find algoritm", e);
        }
        return generatedPassword;
    }

    public static void main(String[] args) {
//        String password = "qwerty123";
//        System.out.println(getSHA512SecurePassword(password, "salt"));

        for (int i = 1000; i <= 9999; i++) {
            String password = String.valueOf(i);
            System.out.println("для password = " + i + " хэш = " + getSHA512SecurePassword(password , "Salt"));
        }
    }

    public static String getRandomSalt() {
        byte[] array = new byte[6];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}

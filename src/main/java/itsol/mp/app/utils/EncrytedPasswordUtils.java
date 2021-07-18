package itsol.mp.app.utils;

import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public class EncrytedPasswordUtils {
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        String encrytedPassword = encrytePassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);


    }


}

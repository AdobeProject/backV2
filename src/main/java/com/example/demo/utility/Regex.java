package com.example.demo.utility;

import com.example.demo.exception.InvalidArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static Pattern VALID_EMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void validEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL.matcher(emailStr);
        if (!matcher.find()) {
            throw new InvalidArgumentException();
        }
    }

    public static void validPassword(String password) {
        if (password.length() < 6) {
            throw new InvalidArgumentException();
        }
    }

    public static void validFirstName(String name) {
        if(name.length() < 2 || name.length() > 20) {
            throw new InvalidArgumentException();
        }
    }

    public static void validSecondName(String surname) {
        if(surname.length() < 2 || surname.length() > 20) {
            throw new InvalidArgumentException();
        }
    }
}

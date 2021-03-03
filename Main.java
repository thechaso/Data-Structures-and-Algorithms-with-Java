package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String numbers = "0123456789";
        String password = "abcd";
        boolean hasDigit = false;
        for (int i = 0; i < password.length(); i++) {
            if (numbers.contains(Character.toString(password.charAt(i)))) hasDigit = true;
        }
    }
}

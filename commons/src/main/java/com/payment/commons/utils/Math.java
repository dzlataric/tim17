package com.payment.commons.utils;

import java.util.Random;

public class Math {

    /**
     * Generates a random int with n digits
     */
    public static int generateRandomDigits(int n) {
        int m = (int) java.lang.Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

}

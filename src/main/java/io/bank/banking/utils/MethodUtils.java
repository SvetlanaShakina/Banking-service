package io.bank.banking.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MethodUtils {
    private static final long LIMIT = 10000000000000L;
    private static long lastNumber = 0;

    public static String generateUniqueAccountNumber() {
        long number = System.currentTimeMillis() % LIMIT;
        if (number <= lastNumber) {
            number = (lastNumber + 1) % LIMIT;
        }
        lastNumber = number;
        return String.valueOf(lastNumber);
    }
}

package io.bank.banking.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String ACCOUNT_NOT_FOUND_ERROR_MESSAGE = "Account with number %s not found";
    public static final String WRONG_NUMBER_OR_PIN_ERROR_MESSAGE = "Account with number %s not found. Check account number or pin";
    public static final String NOT_ENOUGH_MONEY_ERROR_MESSAGE = "There are not enough funds on account number %s for debiting";
}

package io.bank.banking.utils;

import lombok.experimental.UtilityClass;

/**
 * Request url paths
 */
@UtilityClass
public class Paths {
    public static final String PREFIX_ACCOUNTS = "/api/bank-service";
    public static final String CREATE_ACCOUNT_PATH = PREFIX_ACCOUNTS + "/accounts";
    public static final String ACCOUNTS_LIST_PATH = PREFIX_ACCOUNTS + "/accounts";
    public static final String DEPOSIT_MONEY_PATH = PREFIX_ACCOUNTS + "/accounts/deposit";
    public static final String WITHDRAW_MONEY_PATH = PREFIX_ACCOUNTS + "/accounts/withdraw";
    public static final String TRANSFER_MONEY_PATH = PREFIX_ACCOUNTS + "/accounts/transfer";
}

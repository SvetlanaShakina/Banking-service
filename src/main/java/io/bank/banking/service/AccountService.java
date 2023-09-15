package io.bank.banking.service;

import io.bank.banking.dto.request.AccountDto;
import io.bank.banking.dto.request.DepositRequest;
import io.bank.banking.dto.request.TransferRequest;
import io.bank.banking.dto.request.WithdrawRequest;
import io.bank.banking.dto.response.AccountDtoResponse;
import io.bank.banking.dto.response.AccountWithInfoDtoResponse;

import java.util.List;

public interface AccountService {

    /**
     * Create new account
     *
     * @param request - dto to create a new account containing pin and name
     * @return - response with created account
     */
    AccountDtoResponse createAccount(AccountDto request);

    /**
     * Find all accounts
     *
     * @return - list of accounts
     */
    List<AccountWithInfoDtoResponse> findAll();

    /**
     * Deposit money on account
     *
     * @param depositRequest - request for deposit
     * @return - response after deposit with updated balance
     */
    AccountDtoResponse depositMoney(DepositRequest depositRequest);

    /**
     * Withdraw money from account
     *
     * @param withdrawRequest - request for withdraw
     * @return - response after debit with updated balance
     */
    AccountDtoResponse withdrawMoney(WithdrawRequest withdrawRequest);

    /**
     * Transfer money from account to another
     *
     * @param transferRequest - request for transfer
     * @return - response after transfer
     */
    AccountDtoResponse transferMoney(TransferRequest transferRequest);
}
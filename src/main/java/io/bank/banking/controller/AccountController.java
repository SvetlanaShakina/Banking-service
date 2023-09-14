package io.bank.banking.controller;

import io.bank.banking.dto.request.AccountDto;
import io.bank.banking.dto.request.DepositRequest;
import io.bank.banking.dto.request.TransferRequest;
import io.bank.banking.dto.request.WithdrawRequest;
import io.bank.banking.dto.response.AccountDtoResponse;
import io.bank.banking.dto.response.AccountWithInfoDtoResponse;
import io.bank.banking.service.AccountService;
import io.bank.banking.utils.Paths;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * Create new account
     *
     * @param request - request to create a new account containing pin and name
     * @return - response with account's number and balance
     */
    @PostMapping(Paths.CREATE_ACCOUNT_PATH)
    public AccountDtoResponse createAccount(@RequestBody @Valid AccountDto request) {
        return accountService.createAccount(request);
    }

    /**
     * Find all accounts
     *
     * @return - list of account's with names and balances
     */
    @GetMapping(Paths.ACCOUNTS_LIST_PATH)
    public List<AccountWithInfoDtoResponse> findAll() {
        return accountService.findAll();
    }

    /**
     * Money deposit
     *
     * @param depositRequest - request for deposit containing account's number and amount
     * @return - response with account's number and balance
     */
    @PutMapping(Paths.DEPOSIT_MONEY_PATH)
    public AccountDtoResponse depositMoney(@RequestBody @Valid DepositRequest depositRequest) {
        return accountService.depositMoney(depositRequest);
    }

    /**
     * Money withdraw
     *
     * @param withdrawRequest - request for withdraw money containing account's number, pin and amount
     * @return - response with account's number and balance
     */
    @PutMapping(Paths.WITHDRAW_MONEY_PATH)
    public AccountDtoResponse withdrawMoney(@RequestBody @Valid WithdrawRequest withdrawRequest) {
        return accountService.withdrawMoney(withdrawRequest);
    }

    /**
     * Money transfer
     *
     * @param transferRequest - request for money transfer containing account numbers, pin and amount
     * @return - response with account's number and balance
     */
    @PutMapping(Paths.TRANSFER_MONEY_PATH)
    public AccountDtoResponse transferMoney(@RequestBody @Valid TransferRequest transferRequest) {
        return accountService.transferMoney(transferRequest);
    }
}
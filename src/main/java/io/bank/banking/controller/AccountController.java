package io.bank.banking.controller;

import io.bank.banking.dto.request.AccountDto;
import io.bank.banking.dto.request.DepositRequest;
import io.bank.banking.dto.request.TransferRequest;
import io.bank.banking.dto.request.WithdrawRequest;
import io.bank.banking.dto.response.AccountDtoResponse;
import io.bank.banking.dto.response.AccountWithInfoDtoResponse;
import io.bank.banking.service.AccountService;
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
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Create new account
     *
     * @param request - request to create a new account containing pin and name
     * @return - response with account's number and balance
     */
    @PostMapping("/api/bank-service/accounts/new")
    public AccountDtoResponse createAccount(@RequestBody @Valid AccountDto request) {
        return accountService.createAccount(request);
    }

    /**
     * Find all accounts
     *
     * @return - list of account's with names and balances
     */
    @GetMapping("/api/bank-service/accounts")
    public List<AccountWithInfoDtoResponse> findAll() {
        return accountService.findAll();
    }

    /**
     * Money deposit
     *
     * @param depositRequest - request for deposit containing account's number and amount
     * @return - response with account's number and balance
     */
    @PutMapping("/api/bank-service/deposit")
    public AccountDtoResponse depositMoney(@RequestBody @Valid DepositRequest depositRequest) {
        return accountService.depositMoney(depositRequest);
    }

    /**
     * Money withdraw
     *
     * @param withdrawRequest - request for withdraw money containing account's number, pin and amount
     * @return - response with account's number and balance
     */
    @PutMapping("/api/bank-service/withdraw")
    public AccountDtoResponse withdrawMoney(@RequestBody @Valid WithdrawRequest withdrawRequest) {
        return accountService.withdrawMoney(withdrawRequest);
    }

    /**
     * Money transfer
     *
     * @param transferRequest - request for money transfer containing account numbers, pin and amount
     * @return - response with account's number and balance
     */
    @PutMapping("/api/bank-service/transfer")
    public AccountDtoResponse transferMoney(@RequestBody @Valid TransferRequest transferRequest) {
        return accountService.transferMoney(transferRequest);
    }
}
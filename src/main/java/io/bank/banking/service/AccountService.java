package io.bank.banking.service;

import io.bank.banking.dto.request.AccountDto;
import io.bank.banking.dto.request.DepositRequest;
import io.bank.banking.dto.request.TransferRequest;
import io.bank.banking.dto.request.WithdrawRequest;
import io.bank.banking.dto.response.AccountDtoResponse;
import io.bank.banking.dto.response.AccountWithInfoDtoResponse;

import java.util.List;

public interface AccountService {

    AccountDtoResponse createAccount(AccountDto request);

    List<AccountWithInfoDtoResponse> findAll();

    AccountDtoResponse depositMoney(DepositRequest depositRequest);

    AccountDtoResponse withdrawMoney(WithdrawRequest withdrawRequest);

    AccountDtoResponse transferMoney(TransferRequest transferRequest);
}
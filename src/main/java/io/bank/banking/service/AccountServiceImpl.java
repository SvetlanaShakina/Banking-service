package io.bank.banking.service;

import io.bank.banking.dto.request.AccountDto;
import io.bank.banking.dto.request.DepositRequest;
import io.bank.banking.dto.request.TransferRequest;
import io.bank.banking.dto.request.WithdrawRequest;
import io.bank.banking.dto.response.AccountDtoResponse;
import io.bank.banking.dto.response.AccountWithInfoDtoResponse;
import io.bank.banking.entity.Account;
import io.bank.banking.exception.AccountNotFoundException;
import io.bank.banking.exception.NotEnoughMoneyException;
import io.bank.banking.mapper.AccountMapper;
import io.bank.banking.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static io.bank.banking.utils.Constants.ACCOUNT_NOT_FOUND_ERROR_MESSAGE;
import static io.bank.banking.utils.Constants.NOT_ENOUGH_MONEY_ERROR_MESSAGE;
import static io.bank.banking.utils.Constants.WRONG_NUMBER_OR_PIN_ERROR_MESSAGE;
import static io.bank.banking.utils.MethodUtils.generateUniqueAccountNumber;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper mapper;

    @Override
    public AccountDtoResponse createAccount(AccountDto request) {
        String accountNumber = generateUniqueAccountNumber();
        Account account = mapper.fromDtoToEntity(request, accountNumber);
        log.info("Account with number '{}' was generated", accountNumber);
        return mapper.fromEntityToDtoResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountWithInfoDtoResponse> findAll() {
        return accountRepository.findAll().stream()
                .map(mapper::fromEntityToDtoWithInfoResponse)
                .toList();
    }

    @Override
    public AccountDtoResponse depositMoney(DepositRequest depositRequest) {
        String number = depositRequest.number();
        double amount = depositRequest.amount();

        Account account = accountRepository.findByNumber(number)
                .orElseThrow(() ->
                        new AccountNotFoundException(String.format(ACCOUNT_NOT_FOUND_ERROR_MESSAGE, number)));
        log.info("Account with number '{}' was found", number);
        deposit(account, amount);
        log.info("Amount '{}' credited to account '{}'", amount, number);
        return mapper.fromEntityToDtoResponse(accountRepository.save(account));
    }

    @Override
    public AccountDtoResponse withdrawMoney(WithdrawRequest withdrawRequest) {
        String number = withdrawRequest.number();
        double amount = withdrawRequest.amount();

        Account account = findByNumberAndPin(number, withdrawRequest.pin())
                .orElseThrow(() ->
                        new AccountNotFoundException(String.format(WRONG_NUMBER_OR_PIN_ERROR_MESSAGE, number)));
        log.info("Account with number '{}' was found", number);
        withdraw(account, withdrawRequest.amount());
        log.info("Amount '{}' debited from account '{}'", amount, number);
        return mapper.fromEntityToDtoResponse(accountRepository.save(account));
    }

    @Override
    public AccountDtoResponse transferMoney(TransferRequest transferRequest) {
        String numberOfAccountToWithdraw = transferRequest.numberOfAccountToWithdraw();
        String numberOfAccountToDeposit = transferRequest.numberOfAccountToDeposit();
        double amount = transferRequest.amount();

        Account accountToWithdraw = findByNumberAndPin(numberOfAccountToWithdraw,
                transferRequest.pinOfAccountToWithdraw())
                .orElseThrow(() ->
                        new AccountNotFoundException(String.format(WRONG_NUMBER_OR_PIN_ERROR_MESSAGE, numberOfAccountToWithdraw)));
        log.info("Account to withdraw with number '{}' was found", numberOfAccountToWithdraw);


        Account accountToDeposit = accountRepository.findByNumber(numberOfAccountToDeposit)
                .orElseThrow(() ->
                        new AccountNotFoundException(String.format(ACCOUNT_NOT_FOUND_ERROR_MESSAGE, numberOfAccountToDeposit)));
        log.info("Account to deposit with number '{}' was found", numberOfAccountToDeposit);

        if (accountToWithdraw.getBalance() >= amount) {
            withdraw(accountToWithdraw, amount);
            deposit(accountToDeposit, amount);
        } else {
            log.error("There are not enough funds on account '{}' for debiting", numberOfAccountToWithdraw);
            throw new NotEnoughMoneyException(String.format(NOT_ENOUGH_MONEY_ERROR_MESSAGE, accountToWithdraw.getNumber()));
        }
        accountRepository.save(accountToDeposit);
        return mapper.fromEntityToDtoResponse(accountRepository.save(accountToWithdraw));
    }

    private Optional<Account> findByNumberAndPin(String number, String pin) {
        return accountRepository.findByNumberAndPin(number, pin);
    }

    private void withdraw(Account account, double amount) {
        double balance = account.getBalance();
        if (balance >= amount) {
            account.setBalance(balance - amount);
        } else {
            log.error("There are not enough funds on account '{}' for debiting", account.getNumber());
            throw new NotEnoughMoneyException(String.format(NOT_ENOUGH_MONEY_ERROR_MESSAGE, account.getNumber()));
        }
    }

    private void deposit(Account account, Double amount) {
        account.setBalance(account.getBalance() + amount);
    }
}
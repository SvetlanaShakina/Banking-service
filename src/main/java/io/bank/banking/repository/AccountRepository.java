package io.bank.banking.repository;

import io.bank.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumberAndPin(String number, String pin);

    Optional<Account> findByNumber(String number);
}

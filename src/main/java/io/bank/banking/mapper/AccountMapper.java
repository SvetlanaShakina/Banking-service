package io.bank.banking.mapper;

import io.bank.banking.dto.request.AccountDto;
import io.bank.banking.dto.response.AccountDtoResponse;
import io.bank.banking.dto.response.AccountWithInfoDtoResponse;
import io.bank.banking.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account fromDtoToEntity(AccountDto accountDto, String number);

    AccountDtoResponse fromEntityToDtoResponse(Account account);

    AccountWithInfoDtoResponse fromEntityToDtoWithInfoResponse(Account account);
}

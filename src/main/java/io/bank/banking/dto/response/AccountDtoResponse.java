package io.bank.banking.dto.response;

public record AccountDtoResponse(
        String number,
        double balance
) {
}

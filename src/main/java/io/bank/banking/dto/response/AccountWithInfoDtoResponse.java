package io.bank.banking.dto.response;

public record AccountWithInfoDtoResponse(
        String name,
        double balance
) {
}

package io.bank.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record WithdrawRequest(

        @Pattern(regexp = "^\\d+$", message = "Account number must contain only numbers")
        @NotBlank(message = "Account number shouldn't be empty")
        String number,

        @Pattern(regexp = "^\\d{4}$", message = "Pin code must consist of 4 digits")
        String pin,

        @Positive(message = "Amount must be a positive number")
        double amount
) {
}

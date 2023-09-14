package io.bank.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record TransferRequest(
        @Pattern(regexp = "^\\d+$", message = " Number of account to withdraw must contain only numbers")
        @NotBlank(message = "Account number shouldn't be empty")
        String numberOfAccountToWithdraw,

        @Pattern(regexp = "^\\d{4}$", message = "Pin code must consist of 4 digits")
        String pinOfAccountToWithdraw,

        @Pattern(regexp = "^\\d+$", message = "Account number must contain only numbers")
        @NotBlank(message = "Account number shouldn't be empty")
        String numberOfAccountToDeposit,

        @Positive(message = "Amount must be a positive number")
        double amount
) {
}

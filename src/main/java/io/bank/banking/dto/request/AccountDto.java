package io.bank.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AccountDto(
        @Pattern(regexp = "^\\d{4}$", message = "Pin code must consist of 4 digits")
        String pin,

        @NotBlank(message = "Name mustn't be empty")
        String name
) {
}

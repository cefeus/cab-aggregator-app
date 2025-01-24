package com.modsen.passengerservice.dto.request;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record PassengerRequest(
        @NotBlank(message = "firstName.not.blank")
        @Size(min = 2, max = 100, message = "firstName.size")
        String firstname,
        @NotBlank(message = "lastName.not.blank")
        @Size(min = 2, max = 100, message = "lastName.size")
        String lastname,
        @Nullable
        @Email(message = "email.valid")
        String email
) {
}

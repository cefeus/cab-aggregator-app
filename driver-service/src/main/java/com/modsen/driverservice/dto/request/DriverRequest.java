package com.modsen.driverservice.dto.request;


import com.modsen.driverservice.dto.annotation.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import static com.modsen.driverservice.util.RegexpConstants.PHONE_REGEXP;

@Builder
@Schema(description = "Data Transfer Object Request for drivers")
public record DriverRequest(

        @NotBlank(message = "{firstName.not.blank}")
        @Size(min = 2, max = 100, message = "{firstName.size}")
        @Schema(description = "First name of the driver", example = "Petr")
        String firstName,

        @NotBlank(message = "{lastName.not.blank}")
        @Size(min = 2, max = 100, message = "{lastName.size}")
        @Schema(description = "Last name of the driver", example = "Petrov")
        String lastName,

        @Pattern(regexp = PHONE_REGEXP,
                message = "{phone.incorrect.format}")
        @Schema(description = "Driver's phone number", example = "+375339223636")
        String phone,

        @Email(message = "{email.valid}")
        @NotBlank
        @Schema(description = "Driver's email", example = "petrov@gmail.com")
        String email,

        @Sex(message = "{sex.valid}")
        @NotBlank
        @Schema(description = "Drivers's sex", example = "MALE")
        String sex
) {
}

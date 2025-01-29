package com.modsen.passengerservice.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(description = "Data Transfer Object for exceptions")
public record ErrorResponse(
        @Schema(description = "Error unique id",
                example = "791d2b24-5cf5-47ed-b296-02af565ae43b")
        String id,
        @Schema(description = "Error message describing the issue",
                example = "Resource not found")
        String message,
        @Schema(description = "HTTP status of the error",
                example = "404")
        int statusCode,
        @Schema(description = "Timestamp of error occurrence",
                example = "2023-09-30T15:30:00")
        LocalDateTime timestamp
) {
}

package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "Not blank - null - empty")
        String username,
        @Size(min = 3, max = 10)
        String password
) {
}

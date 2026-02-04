package com.tasklist.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(@Email(message = "Email should be valid!") String email,
                @NotBlank(message = "Password can not be blank!") String password) {

}

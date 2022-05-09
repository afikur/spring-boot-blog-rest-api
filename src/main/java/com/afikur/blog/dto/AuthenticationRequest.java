package com.afikur.blog.dto;

import javax.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank
        String usernameOrEmail,

        @NotBlank
        String password
) {
}

package com.afikur.blog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record PostRequest(
        @NotBlank
        @Size(min = 10, message = "length must be at least 10")
        String title,

        @NotBlank
        @Size(min = 30, message = "length must be at least 30")
        String body) {
}

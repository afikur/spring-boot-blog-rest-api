package com.afikur.blog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank
        @Size(min = 3, message = "length must be at least 3")
        String name) {
}

package com.afikur.blog.dto;

import java.util.List;

public record ExceptionResponse(
        String error,
        List<String> messages
) {
}

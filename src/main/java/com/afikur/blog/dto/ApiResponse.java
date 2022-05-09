package com.afikur.blog.dto;

public record ApiResponse(
        Boolean success,
        String message
) {
}

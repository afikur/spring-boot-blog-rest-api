package com.afikur.blog.dto;

import java.util.List;

public record PagedResponse<E>(
        List<E> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
}

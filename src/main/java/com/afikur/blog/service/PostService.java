package com.afikur.blog.service;

import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.model.Post;

import java.util.Optional;

public interface PostService {
    PagedResponse<Post> findAll(int pageNumber, int size);

    Post save(Post post);

    Optional<Post> findById(Long id);
}

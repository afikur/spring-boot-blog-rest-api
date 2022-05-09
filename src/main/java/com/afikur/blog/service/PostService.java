package com.afikur.blog.service;

import com.afikur.blog.dto.ApiResponse;
import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.dto.PostRequest;
import com.afikur.blog.model.Post;

public interface PostService {
    PagedResponse<Post> findAll(int pageNumber, int size);

    Post save(Post post);

    Post findById(Long id);

    ApiResponse deleteById(Long id);

    Post update(PostRequest postRequest, Long id);

    PagedResponse<Post> findPostsByUsername(String username, int page, int size);
}

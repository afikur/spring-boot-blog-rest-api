package com.afikur.blog.mapper;

import com.afikur.blog.dto.PostRequest;
import com.afikur.blog.model.Post;

public interface PostMapper {
    Post toPost(PostRequest postRequest);
}

package com.afikur.blog.mapper.impl;

import com.afikur.blog.dto.PostRequest;
import com.afikur.blog.mapper.PostMapper;
import com.afikur.blog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements PostMapper {
    @Override
    public Post toPost(PostRequest postRequest) {
        if (postRequest == null) {
            return null;
        }

        Post post = new Post();
        post.setTitle(postRequest.title());
        post.setBody(postRequest.body());

        return post;
    }
}

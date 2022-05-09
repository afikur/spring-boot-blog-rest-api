package com.afikur.blog.service.impl;

import com.afikur.blog.dto.ApiResponse;
import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.dto.PostRequest;
import com.afikur.blog.exception.ResourceNotFoundException;
import com.afikur.blog.mapper.PostMapper;
import com.afikur.blog.model.Category;
import com.afikur.blog.model.Post;
import com.afikur.blog.model.User;
import com.afikur.blog.repository.CategoryRepository;
import com.afikur.blog.repository.PostRepository;
import com.afikur.blog.repository.UserRepository;
import com.afikur.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final PostMapper postMapper;

    @Override
    public PagedResponse<Post> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<Post> postsPage = postRepository.findAll(pageable);

        List<Post> posts = postsPage.getTotalElements() == 0 ? Collections.emptyList() : postsPage.getContent();

        return new PagedResponse<>(posts, postsPage.getNumber(), postsPage.getSize(),
                postsPage.getTotalElements(), postsPage.getTotalPages(), postsPage.isLast());
    }

    @Override
    public Post save(PostRequest postRequest) {
        Category category = categoryRepository.findById(postRequest.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("category not found with id " + postRequest.categoryId()));
        Post post = postMapper.toPost(postRequest);
        post.setCategory(category);
        category.addPost(post);
        return postRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        postRepository.delete(post);

        return new ApiResponse(Boolean.TRUE, "Post has been deleted successfully");
    }

    @Override
    public Post update(PostRequest postRequest, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        post.setTitle(postRequest.title());
        post.setBody(postRequest.body());

        return postRepository.save(post);
    }

    @Override
    public PagedResponse<Post> findPostsByUsername(String username, int page, int size) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username " + username));

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Post> postPage = postRepository.findByUser(user, pageable);

        List<Post> posts = postPage.getTotalElements() == 0 ? Collections.emptyList() : postPage.getContent();

        return new PagedResponse<>(posts, postPage.getNumber(), postPage.getSize(), postPage.getTotalElements(),
                postPage.getTotalPages(), postPage.isLast());
    }
}

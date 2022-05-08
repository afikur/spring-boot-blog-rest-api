package com.afikur.blog.controller;

import com.afikur.blog.PostRepository.PostRepository;
import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.dto.PostRequest;
import com.afikur.blog.mapper.PostMapper;
import com.afikur.blog.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @GetMapping
    public PagedResponse<Post> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Post> postsPage = postRepository.findAll(pageable);
        System.out.println(postsPage.getContent());
        List<Post> posts = postsPage.getTotalElements() == 0 ? Collections.emptyList() : postsPage.getContent();
        return new PagedResponse<>(posts, postsPage.getNumber(), postsPage.getSize(), postsPage.getTotalElements(), postsPage.getTotalPages(), postsPage.isLast());
    }

    @PostMapping
    public Post add(@Valid @RequestBody PostRequest postRequest) {
        Post post = postMapper.toPost(postRequest);
        return postRepository.save(post);
    }

}

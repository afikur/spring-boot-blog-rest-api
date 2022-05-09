package com.afikur.blog.controller;

import com.afikur.blog.dto.ApiResponse;
import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.dto.PostRequest;
import com.afikur.blog.mapper.PostMapper;
import com.afikur.blog.model.Post;
import com.afikur.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostMapper postMapper;
    private final PostService postService;

    @GetMapping
    public PagedResponse<Post> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "30") int size) {
        return postService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable("id") Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable("id") Long id) {
        return postService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Post update(@RequestBody PostRequest postRequest, @PathVariable("id") Long id) {
        return postService.update(postRequest, id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Post add(@Valid @RequestBody PostRequest postRequest) {
        Post post = postMapper.toPost(postRequest);
        return postService.save(post);
    }

}

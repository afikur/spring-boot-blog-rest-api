package com.afikur.blog.service.impl;

import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.model.Post;
import com.afikur.blog.repository.PostRepository;
import com.afikur.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public PagedResponse<Post> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<Post> postsPage = postRepository.findAll(pageable);

        List<Post> posts = postsPage.getTotalElements() == 0 ? Collections.emptyList() : postsPage.getContent();

        return new PagedResponse<>(posts, postsPage.getNumber(), postsPage.getSize(),
                postsPage.getTotalElements(), postsPage.getTotalPages(), postsPage.isLast());
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
}

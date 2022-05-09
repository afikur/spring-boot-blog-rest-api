package com.afikur.blog.controller;

import com.afikur.blog.dto.ApiResponse;
import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.model.Post;
import com.afikur.blog.model.User;
import com.afikur.blog.service.PostService;
import com.afikur.blog.service.UserService;
import com.afikur.blog.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{username}")
    public User updateUser(@Valid @RequestBody User newUser,
                           @PathVariable(value = "username") String username) {

        return userService.updateUser(newUser, username);
    }

    @DeleteMapping("/{username}")
    public ApiResponse deleteUser(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
        return new ApiResponse(Boolean.TRUE, "User has been deleted successfully");
    }

    @GetMapping("/{username}/posts")
    public PagedResponse<Post> findPostsByUsername(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "page", required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int size
    ) {
        return postService.findPostsByUsername(username, page, size);
    }
}


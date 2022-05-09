package com.afikur.blog.service;

import com.afikur.blog.model.User;

public interface UserService {
    User save(User user);

    User updateUser(User newUser, String username);

    void deleteByUsername(String username);
}

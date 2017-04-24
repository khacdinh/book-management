package com.bookmanagement.service;

import com.bookmanagement.entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

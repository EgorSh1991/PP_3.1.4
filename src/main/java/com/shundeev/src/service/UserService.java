package com.shundeev.src.service;

import com.shundeev.src.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addNewUser(User user);

    void deleteUserById(Long id);

    void editUser(User user);

    User findUserById(Long id);

    User findUserByEmail(String email);

}
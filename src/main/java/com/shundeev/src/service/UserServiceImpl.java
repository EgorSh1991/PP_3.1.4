package com.shundeev.src.service;

import com.shundeev.src.dao.UserDAO;
import com.shundeev.src.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.encoder = encoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void addNewUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDAO.addNewUser(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }

    @Transactional
    @Override
    public void editUser(User user) {
        String oldPassword = findUserById(user.getId()).getPassword();
        String newPassword = user.getPassword();
        if (!oldPassword.equals(newPassword)) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userDAO.editUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDAO.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

}

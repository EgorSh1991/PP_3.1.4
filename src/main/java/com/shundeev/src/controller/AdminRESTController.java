package com.shundeev.src.controller;

import com.shundeev.src.models.User;
import com.shundeev.src.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {

    private final UserService userService;

    @Autowired
    public AdminRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public List<User> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }

    @GetMapping("/admin/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/admin")
    public User addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
        return user;
    }

    @DeleteMapping("/admin/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }


    @PutMapping("/admin")
    public User updateUser(@RequestBody User user) {
        userService.editUser(user);
        return user;
    }
}

package com.shundeev.src.controller;

import com.shundeev.src.models.Role;
import com.shundeev.src.models.User;
import com.shundeev.src.service.RoleService;
import com.shundeev.src.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String allUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getListRoles());
        return "index";
    }

    @PostMapping("/admin/new")
    public String addUser(@RequestParam("listRoles") List<Long> idRoles,
                          User user) {
        Set<Role> roleList = new HashSet<>();
        for (Long id : idRoles) {
            roleList.add(roleService.findRoleById(id));
        }
        user.setRoles(roleList);
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("listRoles") List<Long> rolesId) {
        Set<Role> listRoles = new HashSet<>();
        for (Long idRole : rolesId) {
            listRoles.add(roleService.findRoleById(idRole));
        }
        user.setRoles(listRoles);
        userService.editUser(user);
        return "redirect:/admin";
    }
}


package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.ModuleService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.Module;


import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    // Display all users
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list"; // Points to `src/main/resources/templates/users/list.html`
    }

    // Display a form to add a new user
    @GetMapping("/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/new"; // Points to `src/main/resources/templates/users/new.html`
    }

    // Handle form submission for adding a new user
    @PostMapping
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users"; // Redirect to the list of users
    }


    // Delete a user by ID
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users"; // Redirect to the list of users
    }

    // Find user by username
    @GetMapping("/search")
    public String getUserByUsername(@RequestParam("username") String username, Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "users/search-result"; // Points to `src/main/resources/templates/users/search-result.html`
    }

    @GetMapping("/{id}/profile")
    public String getUserProfile(@PathVariable Long id, Model model) {
        // Fetch user by ID
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users"; // Handle the case where user is not found
        }

        // Fetch modules and tasks associated with the user
        List<Module> modules = moduleService.getModulesByUserId(id);

        // Pass data to the model
        model.addAttribute("user", user);
        model.addAttribute("modules", modules);

        return "users/profile"; // Points to the updated profile HTML
    }

    @GetMapping("/{id}")
    public String showUserProfile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id); // Fetch the user by ID
        model.addAttribute("user", user);
        return "users/profile"; // Name of the Thymeleaf view for user profile
    }
}

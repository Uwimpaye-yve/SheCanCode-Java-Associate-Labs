package com.taskt.crud.controller;

import com.taskt.crud.model.User;
import com.taskt.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers() { return service.getAll(); }

    @PostMapping
    public User createUser(@RequestBody User user) { return service.create(user); }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) { return service.update(id, user); }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { service.delete(id); }
}
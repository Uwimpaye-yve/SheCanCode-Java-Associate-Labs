package com.taskt.crud.service;

import com.taskt.crud.model.User;
import com.taskt.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getAll() { return repository.findAll(); }
    public Optional<User> getById(Long id) { return repository.findById(id); }
    public User create(User user) { return repository.save(user); }
    public User update(Long id, User details) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(details.getName());
        user.setEmail(details.getEmail());
        return repository.save(user);
    }
    public void delete(Long id) { repository.deleteById(id); }
}
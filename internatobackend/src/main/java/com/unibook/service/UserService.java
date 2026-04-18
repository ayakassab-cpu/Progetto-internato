package com.unibook.service;

import com.unibook.entity.User;
import com.unibook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User save(User u) {
        return repository.save(u);
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User non trovato"));
    }
}
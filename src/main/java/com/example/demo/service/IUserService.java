package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
        public List<User> getAll();
            public void saveUser(User user);
            public void deleteUser(int id);
            public Optional<User> findUserById(int id);
}

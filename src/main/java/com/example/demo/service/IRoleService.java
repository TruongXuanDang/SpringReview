package com.example.demo.service;

import com.example.demo.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoleService {
    public List<Role> getAll();

    void saveRole(Role role);
    void deleteRole(int id);
    Role findRoleById(int id);
    Page<Role> findAll(Pageable pageable);
}

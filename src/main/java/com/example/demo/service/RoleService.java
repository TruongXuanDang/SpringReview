package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    @Autowired
    IRoleRepository roleRepository;
        @Override
        public List<Role> getAll() {
            return (List<Role>)roleRepository.findAll();
        }

        @Override
        public void saveRole(Role role) {
            roleRepository.save(role);
        }

        @Override
        public void deleteRole(int id) {
            roleRepository.deleteById(id);
        }

        @Override
        public Role findRoleById(int id) {
            return roleRepository.findById(id).get();
        }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}

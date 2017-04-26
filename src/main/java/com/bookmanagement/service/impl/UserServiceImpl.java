package com.bookmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmanagement.entity.Role;
import com.bookmanagement.entity.User;
import com.bookmanagement.model.UserForm;
import com.bookmanagement.repository.RoleRepository;
import com.bookmanagement.repository.UserRepository;
import com.bookmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserForm userForm;

    @Override
    public void save(User user) {
        Role role = new Role();
        role.setRoleName("ADMIN");
        roleRepository.save(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findAll().get(0));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        userForm.setUserName(username);
        return userRepository.findByUsername(username);
    }
}

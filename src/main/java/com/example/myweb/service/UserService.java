package com.example.myweb.service;

import com.example.myweb.model.Role;
import com.example.myweb.model.User;
import com.example.myweb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled("1");
        Role role = new Role();
        role.setNo_seq((long) 1);
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}

package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.repositories.RoleRepository;
import com.geekbrains.geekmarketwinter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsrService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User findByName(String username) {
        return userRepository.findOneByUserName(username);
    }

    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public boolean save(User user) {
        userRepository.save(user);
        return true;
    }

    @Transactional
    public Iterable<User> findAll() {

        return userRepository.findAll();
    }

    public void delete(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user!= null){
            userRepository.delete(user.get());
        }
    }

}

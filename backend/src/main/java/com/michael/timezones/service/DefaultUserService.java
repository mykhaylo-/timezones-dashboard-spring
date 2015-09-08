package com.michael.timezones.service;

import com.michael.timezones.model.User;
import com.michael.timezones.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean userExists(String username) {
        return getUser(username) != null;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}

package com.michael.timezones.service;

import com.michael.timezones.model.User;

public interface UserService {

    User getUser(String username);

    boolean userExists(String username);

    void addUser(User user);
}

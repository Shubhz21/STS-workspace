package com.examseries.service;

import com.examseries.entity.User;
import java.util.List;

public interface UserService {
    User registerUser(User user);
    User login(String email, String password);
    User findByEmail(String email);
    List<User> getAllUsers();
}

package com.studentsp.studentboot.services;

import java.util.List;

import com.studentsp.studentboot.model.User;

public interface UserServices {
    User saveUser(User user);
    List<User> getAllUser();
    User getUserById(int id);
    User updateUser(User user, int id);
    void deleteUser(int id);
    User findByEmail(String email);
    User statusChange(User user, int id);
    User adminRg(User user, int id);
    User adminCf(User user, int id);
    User adminCancle(User user, int id);
    List<User> getAllUserByStatus(String status);
}

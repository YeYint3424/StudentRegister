package com.studentsp.studentboot.impli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsp.studentboot.model.User;
import com.studentsp.studentboot.repo.UserRepo;
import com.studentsp.studentboot.services.UserServices;

@Service
public class UserImpli implements UserServices {

    private final UserRepo userRepo;

    @Autowired
    public UserImpli(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user, int id) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            return userRepo.save(existingUser);
        }
        return null;
    }

    @Override
    public User statusChange(User user, int id) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole("delete");
            existingUser.setStatus("0");
            return userRepo.save(existingUser);
        }
        return null;
    }
    @Override
    public User adminRg(User user, int id) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setStatus("2");
            return userRepo.save(existingUser);
        }
        return null;
    }
    @Override
    public User adminCf(User user, int id) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole("admin");
            existingUser.setStatus("3");
            return userRepo.save(existingUser);
        }
        return null;
    }
    @Override
    public User adminCancle(User user, int id) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setStatus("1");
            return userRepo.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<User> getAllUserByStatus(String status) {
        return userRepo.findByStatus(status);
    }
}

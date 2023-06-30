package com.studentsp.studentboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentsp.studentboot.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    List<User> findByStatus(String status);
}

package com.studentsp.studentboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentsp.studentboot.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	Student findByName(String name);
	List<Student> findByStatus(String status);
}


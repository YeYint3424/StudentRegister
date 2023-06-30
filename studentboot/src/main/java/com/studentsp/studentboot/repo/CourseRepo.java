package com.studentsp.studentboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentsp.studentboot.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer>{

}

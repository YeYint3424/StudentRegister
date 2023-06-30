package com.studentsp.studentboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentsp.studentboot.model.Student_Course;

@Repository
public interface Student_CourseRepo extends JpaRepository<Student_Course, Integer> {

    Student_Course findByStudentId(int studentId);

    Student_Course findByCourseId(int courseId);
}

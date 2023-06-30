package com.studentsp.studentboot.services;

import java.util.List;

import com.studentsp.studentboot.model.Student_Course;

public interface Student_CourseServices {

    Student_Course saveSt_cou(Student_Course studentCourse);

    List<Student_Course> getAllSt_cou();

    Student_Course getSt_couById(int id);

    Student_Course findByStudent_id(int studentId);

    Student_Course findByCourse_id(int courseId);
}

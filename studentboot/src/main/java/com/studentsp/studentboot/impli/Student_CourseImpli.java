package com.studentsp.studentboot.impli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsp.studentboot.model.Student_Course;
import com.studentsp.studentboot.repo.Student_CourseRepo;
import com.studentsp.studentboot.services.Student_CourseServices;

@Service
public class Student_CourseImpli implements Student_CourseServices {

    @Autowired
    Student_CourseRepo studentCourseRepo;

    @Override
    public Student_Course saveSt_cou(Student_Course studentCourse) {
        return studentCourseRepo.save(studentCourse);
    }

    @Override
    public List<Student_Course> getAllSt_cou() {
        return studentCourseRepo.findAll();
    }

    @Override
    public Student_Course getSt_couById(int id) {
        return studentCourseRepo.findById(id).orElse(null);
    }

    @Override
    public Student_Course findByStudent_id(int studentId) {
        return studentCourseRepo.findByStudentId(studentId);
    }

    @Override
    public Student_Course findByCourse_id(int courseId) {
        return studentCourseRepo.findByCourseId(courseId);
    }

}

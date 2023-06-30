package com.studentsp.studentboot.services;

import java.util.List;

import com.studentsp.studentboot.model.Student;

public interface StudentServices {
	Student saveStudent(Student student);
//	List<Student> getAllStudent(String status);
	Student getStudentByStudentId(int id);
	Student updateStudent(Student student, int id);
	Student statusChange(Student student, int id);
	Student getStudentByName(String name);
	List<Student> getStudentByStatus1(String status);
}

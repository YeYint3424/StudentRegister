package com.studentsp.studentboot.services;

import java.util.List;

import com.studentsp.studentboot.model.Course;

public interface CourseServices {

	Course saveCourse(Course course);
	List <Course> getAllCourse();
	Course getCourseById(int id);
}

package com.studentsp.studentboot.impli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentsp.studentboot.model.Course;
import com.studentsp.studentboot.repo.CourseRepo;
import com.studentsp.studentboot.services.CourseServices;

@Service
public class CourseImpli implements CourseServices{

	@Autowired
	CourseRepo courseRepo;
	

	@Override
	public Course saveCourse(Course course) {
	    return courseRepo.save(course);
	}


	@Override
	public List<Course> getAllCourse() {
		// TODO Auto-generated method stub
		return courseRepo.findAll();
	}

	@Override
	public Course getCourseById(int id) {
		// TODO Auto-generated method stub
		return courseRepo.findById(id).orElse(null);
	}

}

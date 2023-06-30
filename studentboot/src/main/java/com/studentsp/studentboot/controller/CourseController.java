package com.studentsp.studentboot.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.studentsp.studentboot.model.Course;
import com.studentsp.studentboot.model.User;
import com.studentsp.studentboot.services.CourseServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class CourseController {

	@Autowired
	CourseServices courseServices;
	
	@RequestMapping(value="/course-add")
	public String course_Add(HttpSession session ,Model model) {
		User admin=(User) session.getAttribute("admin");
		if (admin!=null) {
			List<Course> courses=courseServices.getAllCourse();
			model.addAttribute("courses", courses);

			return "admin/course-add";
		}
		return"redirect:/login";
	}
	@RequestMapping(value="/add_Course")
	public String course_Ad(HttpSession session , @RequestParam("course_name")String course_name,@RequestParam("course_info")String course_info,@RequestParam("course_photo")MultipartFile course_photo) throws IOException {
		User admin=(User) session.getAttribute("admin");
		if (admin!=null) {
			Course course=new Course();
			course.setCourse_name(course_name);
			course.setCourse_info(course_info);
			byte[] coursePhoto = course_photo.getBytes();

		    if (coursePhoto != null && coursePhoto.length > 0) {
		        String encodedPhoto = Base64.getEncoder().encodeToString(coursePhoto);
		        course.setCourse_photo(encodedPhoto);
		    }		
		    if(course_name!=null && course_info!=null && course_photo!=null) {
				courseServices.saveCourse(course);
				return"redirect:/admin-home";
			}
			return "admin/course-add";
		}
		return"redirect:/login";
	}
}

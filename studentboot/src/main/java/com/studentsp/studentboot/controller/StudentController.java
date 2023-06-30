package com.studentsp.studentboot.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.studentsp.studentboot.model.Course;
import com.studentsp.studentboot.model.Student;
import com.studentsp.studentboot.model.Student_Course;
import com.studentsp.studentboot.model.User;
import com.studentsp.studentboot.services.CourseServices;
import com.studentsp.studentboot.services.StudentServices;
import com.studentsp.studentboot.services.Student_CourseServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	
	@Autowired
	private StudentServices stuServices;
	
	@Autowired
	private CourseServices courseService;
	
	@Autowired Student_CourseServices st_cService;
	
	@RequestMapping(value="/st_register")
	public String st_register(HttpSession session,Model model) {
		User user=(User) session.getAttribute("user");
		if (user!=null) {
			
			List<Course> courses=courseService.getAllCourse();
			model.addAttribute("courses", courses);
			model.addAttribute("id",user.getId());
			String message=(String) model.getAttribute("message");
			if(message!=null) {
				System.out.println(message);
				model.addAttribute("message", message);
				return "student/register";
			}else {
				model.addAttribute("message", "");
			}
			return "student/register";
		}
		return"/";
	}
	@RequestMapping(value="/st_registration")
	public String st_registration(HttpSession session, Model model, @RequestParam("name") String name,
            @RequestParam("dob") String dob, @RequestParam("gender") String gender,
            @RequestParam("phone") String phone, @RequestParam("education") String education,
            @RequestParam("course_id") int course_id, @RequestParam("st_photo") MultipartFile st_photo,
            @RequestParam("user_id") int user_id, @RequestParam("status") String status) throws IOException {
		User user = (User) session.getAttribute("user");
			if (user != null) {
					
				Student studentn = stuServices.getStudentByName(name);
				if (studentn != null) {
					String message = "Student name already exists";
					model.addAttribute("message", message);
					return "redirect:/st_register";
				} else {
					model.addAttribute("message", "");
				}
					
				Student student = new Student();
				student.setName(name);
				student.setDob(dob);
				student.setGender(gender);
				student.setEducation(education);
				student.setPhone(phone);
				student.setStatus(status);
				student.setUser_id(user_id);
				
				byte[] stPhoto = st_photo.getBytes();
				if (stPhoto != null && stPhoto.length > 0) {
					String encodedPhoto = Base64.getEncoder().encodeToString(stPhoto);
					student.setSt_photo(encodedPhoto);
				}
					
				stuServices.saveStudent(student);
				Student createdStudent = stuServices.getStudentByName(name);
				
				int student_id = createdStudent.getId();					
//				int[] courses = new int[course_id.length];
//				System.arraycopy(course_id, 0, courses, 0, course_id.length);
				

					Course cou=courseService.getCourseById(course_id);
					Student_Course stC = new Student_Course();
					stC.setCourse(cou);
					stC.setStudent(createdStudent);
					st_cService.saveSt_cou(stC);
				

				
				
//				for (int courseId : courses) {
//					Student_Course stC = new Student_Course();
//					stC.setCourse(courseId);
//					stC.setStudentId(student_id);
//					st_cService.saveSt_cou(stC);
//				}
					
				return "redirect:/user-home";
			}
					
		return "redirect:/login";
	}
	
}

package com.studentsp.studentboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentsp.studentboot.model.Course;
import com.studentsp.studentboot.model.Student;
import com.studentsp.studentboot.model.Student_Course;
import com.studentsp.studentboot.model.User;
import com.studentsp.studentboot.services.CourseServices;
import com.studentsp.studentboot.services.StudentServices;
import com.studentsp.studentboot.services.Student_CourseServices;
import com.studentsp.studentboot.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@Autowired
	CourseServices courseServices;
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	Student_CourseServices stCServices;
	
	@Autowired
	StudentServices studentServices;
	
	@RequestMapping(value="/admin-home")
	public String admin_home(HttpSession session,Model model) {
		User admin=(User) session.getAttribute("admin");
		List<Course> courses=courseServices.getAllCourse();
		model.addAttribute("courses", courses);
		if(admin!=null) {
			return "admin/home";
		}else
		return "redirect:/login";
	}
	
	@RequestMapping(value="/userlist")
	public String userlist(HttpSession session,Model model) {
		User admin=(User) session.getAttribute("admin");
		if(admin!=null) {
			List<User> user1=userServices.getAllUserByStatus("2");
			List<User> user2=userServices.getAllUserByStatus("1");
			List<User> users = new ArrayList<>();
			users.addAll(user1);
			users.addAll(user2);
			model.addAttribute("users", users);
			return "admin/user-list";
		}
		return "redirect:/login";
		
	}
	
	@RequestMapping(value="/deleteUser")
	public String userDelete(HttpSession session,@RequestParam("id")int id) {
		User admin=(User) session.getAttribute("admin");
		if (admin!=null) {
			User user=userServices.getUserById(id);
			User deUser=userServices.statusChange(user, id);
			return "redirect:/userlist";
		}return"redirect:/login";
	}
	
	@RequestMapping(value="/addAdmin")
	public String addAdmin(HttpSession session,@RequestParam("id")int id) {
		User admin=(User) session.getAttribute("admin");
		if(admin!=null) {
			User adruser=userServices.getUserById(id);
			User user=userServices.adminCf(adruser, id);
			
			return"redirect:/adminRgList";
		}return"redirect:/login";
	
	}
	@RequestMapping(value="/adminRgList")
	public String admin_rg(HttpSession session,Model model) {
		User admin=(User) session.getAttribute("admin");
		if (admin!=null) {
			List<User> users=userServices.getAllUserByStatus("2");
			model.addAttribute("users", users);
			return "admin/adminRglist";
		}return"redirect:/login";
	}
	@RequestMapping(value="/cancleUser")
	public String cancle(HttpSession session,Model model,@RequestParam("id")int id) {
		User admin=(User)session.getAttribute("admin");
		
		if (admin!=null) {
			User user=userServices.getUserById(id);
			User cuser=userServices.adminCancle(user, id);
			return"redirect:/adminRgList";
		}return"redirect:/login";
	}
	@RequestMapping(value="/adminList")
	public String adminList(HttpSession session,Model model) {
		User admin=(User) session.getAttribute("admin");
		if (admin!=null) {
			List<User> users=userServices.getAllUserByStatus("3");
			model.addAttribute("users", users);
			return "admin/adminList";
		}return"redirect:/login";
	}
	
	@RequestMapping(value = "/studentRg")
	public String stuRg(HttpSession session, Model model) {
	    User admin = (User) session.getAttribute("admin");
	    if (admin != null) {
	        List<Student> students = studentServices.getStudentByStatus1("1");
	        
	        List<Student_Course> stC = stCServices.getAllSt_cou();
	        List<Course> courses = courseServices.getAllCourse();
	        model.addAttribute("courses", courses);
	        model.addAttribute("stC", stC);
	        model.addAttribute("students", students);
	        return "admin/stuRg";
	    }
	    return "redirect:/login";
	}

}

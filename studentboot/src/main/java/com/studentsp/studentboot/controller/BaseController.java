package com.studentsp.studentboot.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentsp.studentboot.model.Course;
import com.studentsp.studentboot.model.User;
import com.studentsp.studentboot.services.CourseServices;
import com.studentsp.studentboot.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BaseController {

	
	@Autowired
	UserServices userServices;
	
	@Autowired
	CourseServices courseServices;
	
	@RequestMapping("/")
	public String view (Model model) {
		List<Course> courses=courseServices.getAllCourse();
		model.addAttribute("courses", courses);

		return "view";
	}
	
	@GetMapping(value="/login")
	public String login(Model model) {
		String error=(String) model.getAttribute("error");
		if (error!=null) {
			model.addAttribute("error",error);
		return "login";
		}
		model.addAttribute("error","");
		return "login";
	}
	@PostMapping(value="/user-login")
	public String ulogin(@RequestParam("email")String email,@RequestParam("password")String password,Model model,HttpSession session) {
		
		User user=userServices.findByEmail(email);
		List<Course> courses=courseServices.getAllCourse();
		model.addAttribute("courses", courses);
		if(user!=null && password.equals(user.getPassword()) && user.getRole().equals("user")) {
			session.setAttribute("user", user);
			return "redirect:/user-home";
		}else if(user!=null && password.equals(user.getPassword()) && user.getRole().equals("admin")) {
			session.setAttribute("admin", user);
			return "redirect:/admin-home";
		}else

	        model.addAttribute("error", "Please Try Again!");
			return"login";
	}
	
	@GetMapping(value="/signup")
	public String signup() {
		return "signup";
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}

}

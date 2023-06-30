package com.studentsp.studentboot.controller;

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

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserServices userServices;
    
    @Autowired
    CourseServices courseServices;

    @PostMapping(value = "/user-signup")
    public String user_signup(@RequestParam("username") String username, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("role") String role,@RequestParam("status")String status, Model model,HttpSession session) {
       
    	User uemail=userServices.findByEmail(email);
    	if(uemail!=null) {
    		model.addAttribute("error", "Email is already in used.");
    		return "signup";
    	}
    	if (username != null && email != null && password != null && role != null) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);
            user.setStatus(status);

            userServices.saveUser(user);
            session.setAttribute("user",user);
            return "redirect:/user-home";
        }
    	model.addAttribute("error", "please try again");

        
        return "signup";
    }
    
    @RequestMapping(value="/user-home")
    public String uhome(Model model,HttpSession session) {
    	User user=(User) session.getAttribute("user");
		List<Course> courses=courseServices.getAllCourse();
		model.addAttribute("courses", courses);
    	if(user!=null) {
    		session.setAttribute("user", user);
    		return "user/home";
    	}else
    		return "redirect:/login";
    }
    
    @RequestMapping(value="/user-profile")
    public String upf(Model model,HttpSession session) {
    	User user=(User) session.getAttribute("user");
    	if(user!=null) {
    		String message=(String) model.getAttribute("done");
    		if (message!=null) {
    			model.addAttribute("done", message);
    		}else if(user.getStatus().equals("2")){
    			model.addAttribute("done", "You already register.");
    		}else {
    			model.addAttribute("done", "");
    		}
    		session.setAttribute("user", user);
    		model.addAttribute("userData", user);
    		return "user/profile";
    	}else
    		return "redirect:/login";
    }
    @RequestMapping(value="/user-profile-edit")
    public String upfudd(Model model, HttpSession session, @RequestParam("npassword") String npassword,
            @RequestParam("role") String role, @RequestParam("username") String username,
            @RequestParam("email") String email, @RequestParam("id") int id, @RequestParam("password") String password) {
        User fuser = (User) session.getAttribute("user");

        if (fuser != null) {

            if (!password.equals(fuser.getPassword())) {
                System.out.println(password);
                String error = "Password is wrong.";
                model.addAttribute("error", error);
                System.out.println(fuser.getPassword());
                return "redirect:/user-profile-update";
            }

            if (username.isEmpty()) {
                username = fuser.getUsername();
            }
            if (email.isEmpty()) {
                email = fuser.getEmail();
            }
            if (!(npassword.isEmpty())) {
                password = npassword;
            }

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            userServices.updateUser(user, id);

            User user1 = new User();
            user1.setId(id);
            user1.setEmail(email);
            user1.setUsername(username);
            user1.setPassword(password);
            user1.setRole(role);
            session.setAttribute("user", user1);
            return "redirect:/user-profile";
        }

        return "redirect:/login";
    }

    @RequestMapping(value="/user-profile-update")
    public String upfud(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String error = (String) model.getAttribute("error");
            if (error != null) {
                System.out.println(error);
                model.addAttribute("error", error);
                session.setAttribute("user", user);
                model.addAttribute("userData", user);
                return "user/profile-update";
            } else {
                model.addAttribute("error", "");
            }

            session.setAttribute("user", user);
            model.addAttribute("userData", user);
            return "user/profile-update";
        }

        return "redirect:/login";
    }


    @RequestMapping(value="/adminRegister")
    public String adReg(HttpSession session,@RequestParam("id")int id,Model model) {
    	User user=(User)session.getAttribute("user");
    	if (user!=null) {
    		User user1=userServices.getUserById(id);
    		User aduser=userServices.adminRg(user1, id);
    		User user2=userServices.getUserById(id);
    		session.setAttribute("user", user2);
    		String message = "Admin Register Successful.";
    	    model.addAttribute("done", message);
    		return"redirect:/user-profile";
    	}return"redirect:/login";
    }
    
    
}

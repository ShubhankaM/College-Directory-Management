package com.college.CollegeDirectory_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.CollegeDirectory_Model.User;
import com.college.CollegeDirectory_Service.UserService;

import ch.qos.logback.core.model.Model;

@Controller
public class LoginController {

	@Autowired
    private UserService userService;

	@GetMapping("/login")
    public String login() {
        return "login"; // Thymeleaf login page
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            if ("STUDENT".equals(user.getRole())) {
                return "redirect:/student/dashboard"; // Modify this to your actual student dashboard
            } else if ("FACULTY_MEMBER".equals(user.getRole())) {
                return "redirect:/faculty/dashboard"; // Modify this to your actual faculty dashboard
            } else if ("ADMINISTRATOR".equals(user.getRole())) {
                return "redirect:/admin/dashboard"; // Modify this to your actual admin dashboard
            }
        }
       // model.addAttribute("error", "Invalid credentials");
        return "login";
    }
}
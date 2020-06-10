package com.example.demo.home.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserMapper;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {
	
@Autowired	
UserService userService;

	@GetMapping("/home")
	public String getHome(Model model, Principal principal) {
		User user = userService.select(principal.getName());
		model.addAttribute(user);
		return "home";
				
		
	}
	

}

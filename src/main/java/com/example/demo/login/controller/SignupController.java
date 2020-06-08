package com.example.demo.login.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {
	@Autowired
	private UserService userService;
	
	private List<String> selectStatus;
	private List<String> initSelectStatus() {
		List<String> select = new ArrayList<String>() {
			{
				add("社会人");
				add("主婦");
				add("大学生");
				add("高校生");
				add("中学生");
				add("小学生");
				add("その他");
			}
		};
		
		return select;		
	}
	
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form, Model model) {
		selectStatus = initSelectStatus();
		model.addAttribute("selectStatus", selectStatus);
		return "login/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,  BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return getSignup(form, model);
		}
		System.out.println(form);
		User user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setNickname(form.getNickname());
		user.setBirthday(form.getBirthday());
		user.setStatus(form.getStatus());
		user.setProfile(form.getProfile());		
		
		boolean result = userService.insert(user);
		
		if(result == true) {
			System.out.println("insert成功");
			return "Saved";
		} else {
			System.out.println("insert失敗");
		}
		
		return "redirect:/login";
				
	}
	
}

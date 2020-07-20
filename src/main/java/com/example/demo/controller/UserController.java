package com.example.demo.controller;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.GroupOrder;
import com.example.demo.domain.model.SignupForm;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserUpdateForm;
import com.example.demo.domain.service.UserService;

@Controller
public class UserController {
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
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,  BindingResult bindingResult, Model model) throws IOException {
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
		} else {
			System.out.println("insert失敗");
		}
		
		return "redirect:/login";
				
	}
	
	@GetMapping("/userDetail/{id}")
	public String getUserDetail(Model model, @PathVariable("id") int id) {
		User user = userService.select(id);
		model.addAttribute("user", user);
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");
		return "myPageLayout";		
	}
	
	@GetMapping("/userEdit/{id}")
	public String getUserEdit(@ModelAttribute UserUpdateForm form, Model model, @PathVariable("id") int id) {
		selectStatus = initSelectStatus();
		model.addAttribute("selectStatus", selectStatus);
		
			User user = userService.select(id);
			form.setId(user.getId());
			form.setEmail(user.getEmail());
			form.setNickname(user.getNickname());
			form.setBirthday(user.getBirthday());
			form.setStatus(user.getStatus());
			form.setProfile(user.getProfile());
			model.addAttribute("userUpdateForm", form);
			model.addAttribute("user",user);
			System.out.println(user);
		model.addAttribute("contents", "login/userEdit :: userEdit_contents");
		return "myPageLayout";		
	}	
	
	@PostMapping(value= "/userEdit", params= "update")
	public String postUserEditUpdate(@ModelAttribute @Validated(GroupOrder.class) UserUpdateForm form, BindingResult bindingResult, Model model){	
		if(bindingResult.hasErrors()) {
			return getUserEdit(form, model, form.getId());
		}		
		User user = new User();
		System.out.println(form);
		user.setId(form.getId());
		user.setEmail(form.getEmail());
		user.setNickname(form.getNickname());
		user.setBirthday(form.getBirthday());
		user.setStatus(form.getStatus());
		user.setProfile(form.getProfile());
		System.out.println(form.getBirthday());		
		System.out.println(user.getBirthday());		
		boolean result = userService.update(user);
		if (result == true) {
			model.addAttribute("result", "ユーザー情報を編集しました");
		} else {
			model.addAttribute("result", "ユーザー情報の編集に失敗しました");
		}
		return getUserEdit(form, model, form.getId());
	}
	
	@PostMapping(value = "/userEdit", params = "delete")
	public String postUserEditDelete(@ModelAttribute SignupForm form, Model model) {

		boolean result = userService.delete(form.getId());
		if (result == true) {
			model.addAttribute("result", "退会しました");
			return getLogin(); 
		} else {
			model.addAttribute("result", "退会に失敗しました");
			return getUserDetail(model, form.getId());
		}
		
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login/login";
	}
	
	@PostMapping("/login")
	public String postLogin(Model model) {
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}
}

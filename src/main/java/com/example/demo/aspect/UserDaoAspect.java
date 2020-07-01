package com.example.demo.aspect;

import java.security.Principal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.example.demo.domain.model.User;
import com.example.demo.domain.service.UserService;

@Component
@Aspect
public class UserDaoAspect {
	
	@Autowired	
	UserService userService;
	
	@Before("execution(* com.example.demo.controller.HomeController.getHome(..))")
	public void getUserInfo() {
	}

}

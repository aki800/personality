package com.example.demo.controller;

import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.model.User;
import com.example.demo.domain.model.Chara;
import com.example.demo.domain.model.Tweet;
import com.example.demo.domain.service.CharaService;
import com.example.demo.domain.service.TweetService;
import com.example.demo.domain.service.UserService;

@Controller
public class HomeController {
	
@Autowired	
UserService userService;

@Autowired
TweetService tweetService;

@Autowired 
CharaService charaService;

	@GetMapping("/home")
	public String getHome(Model model, Principal principal) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);
		
		List<Chara> charas = charaService.selectAll();
		model.addAttribute("charas", charas);
	
		model.addAttribute("contents", "/home :: home_contents");
		return "homeLayout";
		
		
	}
	
	@GetMapping("/charaTweet/{id}")
	public String getCharaTweet(Model model, Principal principal, @PathVariable("id") int id) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);
		
		Chara chara = charaService.selectOne(id);
		model.addAttribute("chara", chara);
		
		List<Tweet> tweets = tweetService.selectInCharacter(id);
		model.addAttribute("tweets", tweets);
		
		model.addAttribute("contents", "/chara/charaTweet :: charaTweet_contents");
		return "homeLayout";
		
	}
	
}

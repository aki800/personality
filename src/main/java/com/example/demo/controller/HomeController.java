package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/")
	public String getHome(Model model, Principal principal) {
		if(principal != null) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);
		}
		
		List<Chara> charas = charaService.selectAll();
		model.addAttribute("charas", charas);
		
		List<Tweet> tweetFromDb = new ArrayList<Tweet>();
		List<Tweet> tweets = new ArrayList<Tweet>();
		for(int i=1; i<=charas.size(); i++) {
		  tweetFromDb = tweetService.selectInCreatedAtLimited(i);
		
		  if(tweetFromDb.size() > 0) {
			for(int s=0; s < tweetFromDb.size(); s++) {
				String initialText = tweetFromDb.get(s).getText();
				String omittedText = initialText.substring(0, 9);
			    tweetFromDb.get(s).setText(omittedText);						  
			}
		  }
		  tweets.addAll(tweetFromDb);
		}		
		model.addAttribute("tweets", tweets);
				
		model.addAttribute("contents", "home :: home_contents");
		return "homeLayout";				
	}
	
	@PostMapping("/tweetSearch")
	public String postTweet(@RequestParam("keyword")String keyword, Model model, Principal principal) {
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		String[] splitKeyword = keyword.split("　");
		List<Tweet> tweetFromDb = tweetService.selectInKeyword(splitKeyword);
		
		  if(tweetFromDb.size() > 0) {
			for(int s=0; s < tweetFromDb.size(); s++) {
				String initialText = tweetFromDb.get(s).getText();
				String omittedText = initialText.substring(0, 9);
			    tweetFromDb.get(s).setText(omittedText);						  
			}
		  }
		 tweets.addAll(tweetFromDb);		  
		 model.addAttribute("tweets", tweets);
		
		return getTweetSearch(model, tweets, principal);
		
	}
	
	@GetMapping("/tweetSearch")
	public String getTweetSearch(Model model, List<Tweet> tweets, Principal principal) {
		if(principal != null) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);
		}
		
		Set<Chara> charas = new HashSet<Chara>();
		Chara chara = null;
		for(int i=0; i < tweets.size(); i++) {
		  chara = charaService.selectInTweet(tweets.get(i).getCharacterId());
		  charas.add(chara);
		}		
		model.addAttribute("charas", charas);	
		
		model.addAttribute("contents", "tweet/tweetSearch :: tweetSearch_contents");
		return "homeLayout";					
	}
	
	
	@GetMapping("/charaTweet/{id}")
	public String getCharaTweet(Model model, Principal principal, @PathVariable("id") int id) {
		if(principal != null) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);
		}
		
		Chara chara = charaService.selectOne(id);
		model.addAttribute("chara", chara);

		List<Tweet> tweets = new ArrayList<Tweet>();
		List<Tweet> tweetFromDb = tweetService.selectInCharacter(id);
			
		  if(tweetFromDb.size() > 0) {
			for(int s=0; s < tweetFromDb.size(); s++) {
				String initialText = tweetFromDb.get(s).getText();
				String omittedText = initialText.substring(0, 9);
			    tweetFromDb.get(s).setText(omittedText);						  
			}
		  }
		  tweets.addAll(tweetFromDb);		  
		  model.addAttribute("tweets", tweets);
		
		model.addAttribute("contents", "chara/charaTweet :: charaTweet_contents");
		return "homeLayout";
		
	}
	
}

package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.SignupForm;
import com.example.demo.domain.model.Tweet;
import com.example.demo.domain.model.TweetForm;
import com.example.demo.domain.model.User;
import com.example.demo.domain.service.TweetService;
import com.example.demo.domain.service.UserService;

@Controller
public class TweetController {
	
	@Autowired
	TweetService tweetService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/tweetCreate")
	public String getTweetCreate(@ModelAttribute TweetForm form, Model model, Principal principal) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);		
		model.addAttribute("contents", "/tweet/tweetCreate :: tweetCreate_contents");
		return "homeLayout";
	}
	
	@PostMapping("/tweetCreate")
	public String postTweetCreate(@ModelAttribute TweetForm form, BindingResult bindingResult, Model model,  Principal principal) {
	  User user = userService.select(principal.getName());
	  if(bindingResult.hasErrors()) {
		return getTweetCreate(form, model, principal);
	  }
	  System.out.println(form);
	  
	  Tweet tweet = new Tweet();
	  
	  tweet.setText(form.getText());
	  tweet.setUser(user);
	  System.out.println(tweet.getUser().getId());
	  boolean result = tweetService.insert(tweet);
	  
	  if(result == true) {
		System.out.println("insert成功");
	  } else {
		System.out.println("insert失敗");
	  }
	  
	  return "redirect:/home";
	  
	}
	
	@GetMapping("/userTweet/{id}")
	public String getUserTweet(Model model, @PathVariable("id") int id) {
		List<Tweet> tweets = tweetService.selectInAuthenticatedUser(id);
		model.addAttribute("tweets", tweets);
		User user = userService.select(id);
		model.addAttribute("user", user);
		model.addAttribute("contents", "/tweet/userTweet :: userTweet_contents");		
		return "myPageLayout";
	}
	@GetMapping("/tweetEdit/{id}")
	public String getTweetEdit(@ModelAttribute TweetForm form, Model model, @PathVariable("id") int id, Principal principal) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);
		
		Tweet tweet = tweetService.selectOne(id);
		form.setId(id);
		form.setText(tweet.getText());
		model.addAttribute("TweetForm", form);	
			
		model.addAttribute("contents", "/tweet/tweetEdit :: tweetEdit_contents");		
		return "myPageLayout";
	}
	
	@PostMapping(value = "/tweetEdit", params = "update")
	public String postTweetEditUpdate(@ModelAttribute TweetForm form, Model model,  Principal principal) {
		
		Tweet tweet = new Tweet();
		tweet.setId(form.getId());
		tweet.setText(form.getText());
		boolean result = tweetService.update(tweet);
		
		if (result == true) {
			model.addAttribute("result", "投稿を編集しました");
		} else {
			model.addAttribute("result", "投稿の編集に失敗しました");
		}
		return getTweetEdit(form, model, tweet.getId(), principal);		
		
	}
	
	@PostMapping(value = "/tweetEdit", params = "delete")
	public String postTweetEditDelete(@ModelAttribute TweetForm form, Model model, Principal principal) {
		boolean result = tweetService.delete(form.getId());

		if (result == true) {
			model.addAttribute("result", "投稿を削除しました");
			User user = userService.select(principal.getName());
			return getUserTweet(model, user.getId());
		} else {
			model.addAttribute("result", "投稿の削除に失敗しました");
			return getTweetEdit(form, model, form.getId(), principal);		
		}
	}
	

}

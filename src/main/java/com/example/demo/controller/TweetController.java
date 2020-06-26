package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.Comment;
import com.example.demo.domain.model.CommentForm;
import com.example.demo.domain.model.GroupOrder;
import com.example.demo.domain.model.Tweet;
import com.example.demo.domain.model.TweetForm;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.Chara;
import com.example.demo.domain.service.CharaService;
import com.example.demo.domain.service.CommentService;
import com.example.demo.domain.service.TweetService;
import com.example.demo.domain.service.UserService;

@Controller
public class TweetController {
	
	@Autowired
	TweetService tweetService;	
	@Autowired
	UserService userService;	
	@Autowired
	CommentService commentService;
	@Autowired
	CharaService charaService;
	
	@GetMapping("/tweetCreate")
	public String getTweetCreate(@ModelAttribute TweetForm form, Model model, Principal principal) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);		
		
		List<Chara> characters = charaService.selectAll();
		model.addAttribute("characters", characters);
		
		model.addAttribute("contents", "/tweet/tweetCreate :: tweetCreate_contents");
		return "homeLayout";
	}
	
	@PostMapping("/tweetCreate")
	public String postTweetCreate(@ModelAttribute @Validated TweetForm form, BindingResult bindingResult, Model model,  Principal principal) {
	  User user = userService.select(principal.getName());
	  
	  if(bindingResult.hasErrors()) {
		return getTweetCreate(form, model, principal);
	  }
	  
	  Tweet tweet = new Tweet();
	  
	  tweet.setText(form.getText());
	  tweet.setUserId(user.getId());
	  tweet.setCharacterId(form.getCharacterId());
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
		
		Set<Chara> charas = new HashSet<Chara>();
		Chara chara = null;
		for(int i=0; i < tweets.size(); i++) {
		  chara = charaService.selectInTweet(tweets.get(i).getCharacterId());
		  charas.add(chara);
		}
		model.addAttribute("charas", charas);
		
		User user = userService.select(id);
		model.addAttribute("user", user);
		model.addAttribute("contents", "/tweet/userTweet :: userTweet_contents");		
		return "myPageLayout";
	}
	
	@GetMapping("/tweetDetail/{id}")
	public String getTweetDetail(CommentForm form, Model model, @PathVariable("id") int id, Principal principal) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);	
		
		Tweet tweet = tweetService.selectOne(id);
		tweet.setId(id);
		model.addAttribute(tweet);
		
		form.setTweetId(id);
		model.addAttribute("commentform", form);		
		
		//コメント一覧を表示
		List<Comment> comments = commentService.selectInOneTweet(id);
		model.addAttribute("comments", comments);		
		
		
		model.addAttribute("contents", "/tweet/tweetDetail :: tweetDetail_contents");		
		return "homeLayout";
		
	}	
	
	//コメント投稿
	@PostMapping(value="/tweetDetail", params="create")
	public String PostTweetDetail(@ModelAttribute @Validated CommentForm form, BindingResult bindingResult,  Model model, Principal principal) {
		if(bindingResult.hasErrors()) {
			  return getTweetDetail(form, model, form.getTweetId(), principal);
		 }			
		User user = userService.select(principal.getName());
		
		Comment comment = new Comment();		
		comment.setText(form.getText());
		comment.setTweetId(form.getTweetId());
		comment.setUserId(user.getId());
			
		System.out.println(comment);
		boolean result = commentService.insert(comment);
		  if(result == true) {
			System.out.println("insert成功");
			model.addAttribute("result", "コメントを投稿しました");
		  } else {
			System.out.println("insert失敗");
			model.addAttribute("result", "コメント投稿に失敗しました");
		  }
		  
		
        return getTweetDetail(form, model, form.getTweetId(), principal);		
		
	}
	
	@GetMapping("/commentEdit/{id}")
	public String getCommentEdit(@ModelAttribute CommentForm form, Model model, @PathVariable("id") int id, Principal principal) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);
		
		Comment comment = commentService.selectOne(id);
		form.setId(comment.getId());
		form.setText(comment.getText());
		form.setTweetId(comment.getTweetId());
		model.addAttribute("commentForm", form);
		
		Tweet tweet = tweetService.selectOne(comment.getTweetId());
		model.addAttribute(tweet);
		
		//コメント一覧を表示
		List<Comment> comments = commentService.selectInOneTweet(comment.getTweetId());
		model.addAttribute("comments", comments);		
		model.addAttribute("contents", "/tweet/commentEdit :: commentEdit_contents");		
		return "homeLayout";
		
	}
	
	@PostMapping(value="/commentEdit", params="update")
	public String postCommentEditUpdate(@ModelAttribute @Validated CommentForm form, BindingResult bindingResult, Model model, Principal principal) {
		if(bindingResult.hasErrors()) {
			  return getCommentEdit(form, model, form.getId(), principal);
		 }			
		
		Comment comment = new Comment();
		comment.setId(form.getId());
		comment.setText(form.getText());
		boolean result = commentService.update(comment);
		
		if (result == true) {
			model.addAttribute("result", "コメントを編集しました");
		} else {
			model.addAttribute("result", "コメントの編集に失敗しました");
		}
		return getTweetDetail(form, model, form.getTweetId(), principal);		
	}
	
	@PostMapping(value = "/commentEdit", params = "delete")
	public String postCommentEditDelete(@ModelAttribute CommentForm form, Model model, Principal principal) {
		boolean result = commentService.delete(form.getId());
		
		if (result == true) {
			model.addAttribute("result", "コメントを削除しました");
			User user = userService.select(principal.getName());
			return getTweetDetail(form, model, form.getTweetId(), principal);
		} else {
			model.addAttribute("result", "コメントの削除に失敗しました");
			return getCommentEdit(form, model, form.getId(), principal);		
		}
	}
	
	@GetMapping("/tweetEdit/{id}")
	public String getTweetEdit(@ModelAttribute TweetForm form, Model model, @PathVariable("id") int id, Principal principal) {
		User user = userService.select(principal.getName());
		model.addAttribute("user", user);
		
		Tweet tweet = tweetService.selectOne(id);
		form.setId(id);
		form.setText(tweet.getText());
		form.setCharacterId(tweet.getCharacterId());
		model.addAttribute("TweetForm", form);
		
		List<Chara> characters = charaService.selectAll();
		model.addAttribute("characters", characters);
		
		//コメント一覧を表示
		List<Comment> comments = commentService.selectInOneTweet(id);
		model.addAttribute("comments", comments);					
		
		model.addAttribute("contents", "/tweet/tweetEdit :: tweetEdit_contents");		
		return "homeLayout";
	}		
	
	@PostMapping(value = "/tweetEdit", params = "update")
	public String postTweetEditUpdate(@ModelAttribute @Validated TweetForm form, BindingResult bindingResult,  Model model,  Principal principal) {
		if(bindingResult.hasErrors()) {
		  return getTweetEdit(form, model, form.getId(), principal);
	    }		
		Tweet tweet = new Tweet();
		tweet.setId(form.getId());
		tweet.setText(form.getText());
		tweet.setCharacterId(form.getCharacterId());
		boolean result = tweetService.update(tweet);
		
		if (result == true) {
			model.addAttribute("result", "投稿を編集しました");
		} else {
			model.addAttribute("result", "投稿の編集に失敗しました");
		}
		return getTweetEdit(form, model, tweet.getId(), principal);		
		
	}
	
	
	@PostMapping(value = "/tweetEdit", params = "delete")
	public String postTweetEditDelete(@ModelAttribute @Validated TweetForm form, BindingResult bindingResult, Model model, Principal principal) {
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

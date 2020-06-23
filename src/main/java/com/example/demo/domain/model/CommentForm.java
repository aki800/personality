package com.example.demo.domain.model;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentForm {
	private int id;
	
	@NotBlank
	private String text;
	
	private int tweetId;

}

package com.example.demo.domain.model;


import lombok.Data;

@Data
public class CommentForm {
	private int id;
	private String text;
	private int tweetId;

}

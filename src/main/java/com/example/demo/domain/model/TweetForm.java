package com.example.demo.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class TweetForm {
	private int id;
	
	@NotBlank
	@Length(min = 10, max = 2000)
	private String text;
	
	@NotNull
	private Integer characterId;
}

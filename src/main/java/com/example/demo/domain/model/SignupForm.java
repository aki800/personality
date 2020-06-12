package com.example.demo.domain.model;


import java.util.Date;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	
	private int id;
	
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String email;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 8, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class)
	private String password;
	
	@NotBlank(groups = ValidGroup1.class)
	private String nickname;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	
	private String status;
	
	private String profile;
}

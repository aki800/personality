package com.example.demo.domain.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserUpdateForm {

	private int id;
	
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String email;
	
	@NotBlank(groups = ValidGroup1.class)
	private String nickname;
		
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	
	private String status;
	
	private String profile;
}

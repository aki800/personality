package com.example.demo.login.domain.model;



import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class User {
	private int id;
	private String email;
	private String password;
	private String nickname;
	private Date birthday;
	private String status;
	private String profile;

}

package com.example.demo.login.domain.model;



import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class User {
	private String email;
	private String password;
	private String nickname;
	private Date birthday;
	private String status;
	private String profile;

}

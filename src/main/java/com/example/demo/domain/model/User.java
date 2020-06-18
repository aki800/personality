package com.example.demo.domain.model;



import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name="authorities")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    
    @Column(name = "username")
	private String email;
    
    @Column(name = "authority")
	private String password;
    
	private String nickname;
		
	private Date birthday;
	
	private String status;
	
	private String profile;
			
}

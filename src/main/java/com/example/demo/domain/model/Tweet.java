package com.example.demo.domain.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
@Table(name="tweet")
public class Tweet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@Length(min = 10, max = 2000)
	private String text;
	
	@Column(name  = "user_id")
    private Integer userId;
	
	@Column(name = "character_id")
	private Integer characterId;
	
}

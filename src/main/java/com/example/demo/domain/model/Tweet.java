package com.example.demo.domain.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import java.util.Date;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
}

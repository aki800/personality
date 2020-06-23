package com.example.demo.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="chara")
public class Chara {

	@Id
	private Integer id;
		
	private String kind;
}

package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper {
	@Insert("INSERT INTO user (" 
	+ "email,"
	+ " password,"
	+ " nickname,"
	+ " birthday," 
	+ " status,"
	+ " profile)"
	+ " VALUES ("
	+ " #{email},"
	+ " #{password},"
	+ " #{nickname},"
	+ " #{birthday},"
	+ " #{status},"
	+ " #{profile})")	
	public boolean insert(User user);
	

		
}

package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper {
	
	@Insert("INSERT INTO authorities (" 
	+ "username," //email
	+ " authority," //password
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
	
	@Select("SELECT id,"
	+ " username AS email," //email
	+ " authority AS password," //password
	+ " nickname,"
	+ " birthday,"
	+ " status,"
	+ " profile"
	+ " FROM authorities"
	+ " WHERE username = #{email}")
	public User selectInEmail(String email);

	
	@Select("SELECT id,"
	+ " username AS email," //email
	+ " authority AS password," //password
	+ " nickname,"
	+ " birthday,"
	+ " status,"
	+ " profile"
	+ " FROM authorities"
	+ " WHERE id = #{id}")
	public User selectInId(int id);
	
	@Update("UPDATE authorities SET"
	+ " username = #{email},"  //email
	+ " authority = #{password}," //password
	+ " nickname = #{nickname},"
	+ " birthday = #{birthday},"
	+ " status = #{status},"
	+ " profile = #{profile}"
	+ " WHERE id = #{id}")
	public boolean update(User user);
	
	@Delete("DELETE FROM authorities WHERE id = #{id}")
	public boolean delete(int id);
}

package com.example.demo.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.model.Tweet;
import com.example.demo.domain.model.User;


@Mapper
public interface TweetMapper {
	
	@Insert("INSERT INTO tweet ("
			+ "text,"
			+ " user_id,"
			+ " character_id)"
			+ " VALUES ("
			+ " #{text},"
			+ " #{userId},"
			+ " #{characterId})")
	public boolean insert(Tweet tweet);
	
	@Select("SELECT id,"
	        + " text"
	        + " FROM tweet"
	        + " WHERE user_id = #{id}")
	public List<Tweet> selectInAuthenticatedUser(int id);

	@Select("SELECT id,"
	        + " text,"
			+ " user_id AS userId,"
			+ " character_id AS characterId"
	        + " FROM tweet"
	        + " WHERE character_id = #{id}")
	public List<Tweet> selectInCharacter(int id);
	
	
	@Select("SELECT id,"
			+ " text"
	        + " FROM tweet"
	        + " WHERE id = #{id}")
	public Tweet selectOne(int id);
	
	@Update("UPDATE tweet SET"
	+ " text = #{text}"  //email
	+ " WHERE id = #{id}")
	public boolean update(Tweet tweet);
	
	@Delete("DELETE FROM tweet WHERE id = #{id}")
	public boolean delete(int id);

}

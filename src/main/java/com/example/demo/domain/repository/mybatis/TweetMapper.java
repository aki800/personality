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
	        + " text,"
			+ " user_id AS userId,"
	        + " character_id AS characterId"
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
			+ " text,"
			+ " user_id AS userId,"
			+ " character_id AS characterId"			
	        + " FROM tweet"
			+ " WHERE character_id = #{i}"
	        + " order by created_at desc"
	        + " limit 3")
	public List<Tweet> selectInCreatedAt(int i);	

	@Select("<script>"
			+ "SELECT id,"
			+ " text,"
			+ " user_id AS userId,"
			+ " character_id AS characterId"			
	        + " FROM tweet"
			+ " WHERE"
			+ "<foreach item=\"array\" index=\"array.length\" collection=\"array\" separator=\"AND\">"
			+ " text LIKE CONCAT('%', #{array} , '%')"
			+ "</foreach>"
	        + "</script>")
	public List<Tweet> selectInKeyword(String[] array);	
	
	@Select("SELECT id,"
			+ " text,"
			+ " user_id AS userId,"
			+ " character_id AS characterId"			
	        + " FROM tweet"
	        + " WHERE id = #{id}")
	public Tweet selectOne(int id);
	
	@Update("UPDATE tweet SET"
	+ " text = #{text},"  //email
	+ " character_id = #{characterId}"		
	+ " WHERE id = #{id}")
	public boolean update(Tweet tweet);
	
	@Delete("DELETE FROM tweet WHERE id = #{id}")
	public boolean delete(int id);

}

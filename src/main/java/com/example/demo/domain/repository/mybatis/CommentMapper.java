package com.example.demo.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.model.Comment;

@Mapper
public interface CommentMapper {
	
	@Insert("INSERT INTO comment ("
			+ "text,"
			+ " tweet_id,"
			+ " user_id,"
			+ " created_at)"
			+ " VALUES ("
			+ " #{text},"
			+ " #{tweetId},"
			+ " #{userId},"
			+ " #{createdAt})")
	public boolean insert(Comment comment);
	
	@Select("SELECT id,"
			+ " text,"
			+ " tweet_id AS tweetId,"
			+ " user_id AS userId"
	        + " FROM comment"
	        + " WHERE tweet_id = #{id}"
	        + " order by created_at desc")
	public List<Comment> selectInOneTweet(int id);
	
	@Select("SELECT id,"
			+ " text,"
			+ " tweet_id AS tweetId,"
			+ " user_id AS userId"
	        + " FROM comment"
	        + " WHERE id = #{id}")
	public Comment selectOne(int id);
	
	@Update("UPDATE comment SET"
	+ " text = #{text}"  
	+ " WHERE id = #{id}")
	public boolean update(Comment comment);
	
	@Delete("DELETE FROM comment WHERE id = #{id}")
	public boolean delete(int id);
	
}

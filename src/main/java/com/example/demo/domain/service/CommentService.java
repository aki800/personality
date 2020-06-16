package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Comment;
import com.example.demo.domain.repository.mybatis.CommentMapper;

@Transactional
@Service
public class CommentService {
	
	@Autowired
	CommentMapper commentMapper;

	public boolean insert(Comment comment){
		return commentMapper.insert(comment);
	}
	
	public List<Comment> selectInOneTweet(int id) {
		return commentMapper.selectInOneTweet(id);
	}
	
	public Comment selectOne(int id) {
		return commentMapper.selectOne(id);
	}
	
	public boolean update(Comment comment) {
		return commentMapper.update(comment);
	}
	
	public boolean delete(int id) {
		return commentMapper.delete(id);
	}
}

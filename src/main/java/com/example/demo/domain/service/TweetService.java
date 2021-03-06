package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Tweet;
import com.example.demo.domain.repository.mybatis.TweetMapper;

@Transactional
@Service
public class TweetService {
	
	@Autowired
	TweetMapper tweetMapper;
	
	public boolean insert(Tweet tweet){

		return tweetMapper.insert(tweet);
	}
	
	public List<Tweet> selectInAuthenticatedUser(int id) {
		return tweetMapper.selectInAuthenticatedUser(id);
	}
	
	public List<Tweet> selectInCharacter(int id) {
		return tweetMapper.selectInCharacter(id);
	}

	public List<Tweet> selectInCreatedAtLimited(int i) {
		return tweetMapper.selectInCreatedAtLimited(i);
	}
	
	public List<Tweet> selectInKeyword(String[] keywords) {
		return tweetMapper.selectInKeyword(keywords);
	}
	
	public Tweet selectOne(int id) {
		return tweetMapper.selectOne(id);
	}
	
	
	public boolean update(Tweet tweet) {
		return tweetMapper.update(tweet);
	}
	
	public boolean delete(int id) {
		return tweetMapper.delete(id);
	}
}

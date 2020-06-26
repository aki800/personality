package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.repository.mybatis.CharaMapper;
import com.example.demo.domain.model.Chara;

@Transactional
@Service
public class CharaService {
	
	@Autowired
	CharaMapper charaMapper;
	
	public List<Chara> selectAll() {
		return charaMapper.selectAll();
	}
	
	public Chara selectInTweet(int tweetId) {
		return charaMapper.selectInTweet(tweetId);
	}
	
	public Chara selectOne(int id) {
		return charaMapper.selectOne(id);
	}

}

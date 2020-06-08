package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.mybatis.UserMapper;

@Transactional
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public boolean insert(User user) {
		return userMapper.insert(user);

	}
	
}

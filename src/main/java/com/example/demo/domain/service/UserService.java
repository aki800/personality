package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserDao;
import com.example.demo.domain.repository.mybatis.UserMapper;

@Transactional
@Service
public class UserService {

//Mybatisを使う場合
	@Autowired
	UserMapper userMapper;

//JDBCを使う場合
//	@Autowired
//	@Qualifier("UserDaoJdbcImpl")
//	UserDao dao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean insert(User user) {
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		return userMapper.insert(user);
		
//		int rowNumber = dao.insert(user);
//		boolean result = false;
//		
//		if(rowNumber > 0) {
//			result = true;
//		}
//		
//		return result;

	}
	
	public User select(String email) {
		return userMapper.selectInEmail(email);
	}

	public User select(int id) {
		return userMapper.selectInId(id);
	}

	public boolean update(User user) {
		return userMapper.update(user);
	}
	
	public boolean delete(int id) {
		return userMapper.delete(id);
	}

	
}

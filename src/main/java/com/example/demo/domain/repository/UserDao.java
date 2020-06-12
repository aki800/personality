package com.example.demo.domain.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.User;


public interface UserDao {
	public int insert(User user) throws DataAccessException;
}

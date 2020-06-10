package com.example.demo.login.domain.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;


@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {
@Autowired
JdbcTemplate jdbc;

@Autowired
PasswordEncoder passwordEncoder;

	@Override
	public int insert(User user) throws DataAccessException {
		String password = passwordEncoder.encode(user.getPassword());
		
		int rowNumber = jdbc.update("INSERT INTO authorities(username," + " authority," + " nickname," + " birthday," + " status," + " profile)" + "VALUES(?,?,?,?,?,?)"
	, user.getEmail(), password, user.getNickname(), user.getBirthday(), user.getStatus(), user.getProfile());
		return rowNumber;
	}
}

package com.example.demo.domain.repository.mybatis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.domain.model.Comment;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Sql("/testdata.sql")
public class TestCommentMapper {
	
	@Autowired
	CommentMapper commentMapper;
	
	@Test
	public void select1() {
		Comment comment = commentMapper.selectOne(100);
		
		assertEquals(100, comment.getId());
		assertEquals("テストのコメント", comment.getText());
		assertEquals(100, comment.getTweetId());
		assertEquals(4, comment.getUserId());		
	}
	
	@Test
	public void select2() {
		List<Comment> comments = commentMapper.selectInOneTweet(100);

		assertEquals(101, comments.get(0).getId());
		assertEquals("テストのコメント2", comments.get(0).getText());
		assertEquals(100, comments.get(0).getTweetId());
		assertEquals(4, comments.get(0).getUserId());	
		
		assertEquals(100, comments.get(1).getId());
		assertEquals("テストのコメント", comments.get(1).getText());
		assertEquals(100, comments.get(1).getTweetId());
		assertEquals(4, comments.get(1).getUserId());		
						
	}
	
	@Test
	public void insert() {
		Comment comment = new Comment();
		
		comment.setId(102);
		comment.setText("テストのコメントする");
		comment.setTweetId(100);
		comment.setUserId(4);
		
		assertTrue(commentMapper.insert(comment));
	}
	
	@Test
	public void update() {
		Comment comment = commentMapper.selectOne(100);
		
		comment.setId(comment.getId());
		comment.setText("テストのコメント新");
		comment.setTweetId(comment.getTweetId());
		comment.setUserId(comment.getUserId());
		
		assertTrue(commentMapper.update(comment));
	}

//削除
}

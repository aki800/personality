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

import com.example.demo.domain.model.Tweet;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Sql("/testdata.sql")
public class TestTweetMapper {
	
	@Autowired
	TweetMapper tweetMapper;
	
	@Test
	public void selectTest1() {
		Tweet tweet = tweetMapper.selectOne(100);
        assertAll("tweet",
                () -> assertEquals(100, tweet.getId()),
                () -> assertEquals("テストの投稿", tweet.getText()),
                () -> assertEquals(4, tweet.getUserId()),
                () -> assertEquals(50, tweet.getCharacterId())
            );	
	}
	
	@Test
	public void selectTest2() {
		List<Tweet> tweets = tweetMapper.selectInAuthenticatedUser(4);
	        assertEquals(102, tweets.get(0).getId());
	        assertEquals("テストの投稿2", tweets.get(0).getText());
	        assertEquals(4, tweets.get(0).getUserId());
	        assertEquals(50, tweets.get(0).getCharacterId());
			
            assertEquals(100, tweets.get(1).getId());
            assertEquals("テストの投稿", tweets.get(1).getText());
            assertEquals(4, tweets.get(1).getUserId());
            assertEquals(50, tweets.get(1).getCharacterId());
	}

	@Test
	public void selectTest3() {
		List<Tweet> tweets = tweetMapper.selectInCharacter(50);
	        assertEquals(102, tweets.get(0).getId());
	        assertEquals("テストの投稿2", tweets.get(0).getText());
	        assertEquals(4, tweets.get(0).getUserId());
	        assertEquals(50, tweets.get(0).getCharacterId());
			
            assertEquals(100, tweets.get(1).getId());
            assertEquals("テストの投稿", tweets.get(1).getText());
            assertEquals(4, tweets.get(1).getUserId());
            assertEquals(50, tweets.get(1).getCharacterId());
	}

	@Test
	public void selectTest4() {
		List<Tweet> tweets = tweetMapper.selectInCreatedAtLimited(50);
		
		    assertEquals(3, tweets.size());
		    
	        assertEquals(102, tweets.get(0).getId());
	        assertEquals("テストの投稿2", tweets.get(0).getText());
	        assertEquals(4, tweets.get(0).getUserId());
	        assertEquals(50, tweets.get(0).getCharacterId());
			
            assertEquals(100, tweets.get(1).getId());
            assertEquals("テストの投稿", tweets.get(1).getText());
            assertEquals(4, tweets.get(1).getUserId());
            assertEquals(50, tweets.get(1).getCharacterId());
            
	        assertEquals(99, tweets.get(2).getId());
	        assertEquals("テストの投稿-1", tweets.get(2).getText());
	        assertEquals(4, tweets.get(0).getUserId());
	        assertEquals(50, tweets.get(0).getCharacterId());   	        	        
	}
	
	@Test
	public void select5() {
		String keyword = "テスト　投稿";
		String[] splitKeyword = keyword.split("　");		
		List<Tweet> tweets = tweetMapper.selectInKeyword(splitKeyword);
		
		assertEquals(4, tweets.size());
	}

	//select整理
	
	@Test
	public void insert() {
		Tweet tweet = new Tweet();
		tweet.setId(101);
		tweet.setText("テストの投稿する");
		tweet.setUserId(4);
		tweet.setCharacterId(50);
		assertTrue(tweetMapper.insert(tweet));
	}
	
	@Test
	public void update() {
		Tweet initTweet = tweetMapper.selectOne(100);
		Tweet tweet = new Tweet();
		
		tweet.setId(initTweet.getId());
		tweet.setText("テストの投稿新");
		tweet.setUserId(initTweet.getUserId());
		tweet.setCharacterId(initTweet.getCharacterId());
		
		assertTrue(tweetMapper.update(tweet));
	}
	
//削除	

}

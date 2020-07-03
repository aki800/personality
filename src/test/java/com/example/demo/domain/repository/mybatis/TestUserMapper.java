package com.example.demo.domain.repository.mybatis;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.domain.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Sql("/testdata.sql")
public class TestUserMapper {

	@Autowired
	UserMapper userMapper;

	@Test
	public void selectTest1(){
		User user = userMapper.selectInEmail("test@xxx.co.jp");
        assertAll("user",
                () -> assertEquals(4, user.getId()),
                () -> assertEquals("test@xxx.co.jp", user.getEmail()),
                () -> assertEquals("テストユーザー", user.getNickname()),
                () -> assertEquals("社会人", user.getStatus()),
                () -> assertEquals("テストユーザーです", user.getProfile())
                //誕生日は省く
            );		
	}
	
	@Test
	public void selectTest2(){
		User user = userMapper.selectInId(4);
        assertAll("user",
                () -> assertEquals(4, user.getId()),
                () -> assertEquals("test@xxx.co.jp", user.getEmail()),
                () -> assertEquals("テストユーザー", user.getNickname()),
                () -> assertEquals("社会人", user.getStatus()),
                () -> assertEquals("テストユーザーです", user.getProfile())
                //誕生日は省く
            );		
	}
	
	@Test
	public void insert() {
		User user = new User();
		user.setId(5);
		user.setEmail("test2@xxx.co.jp");
		user.setPassword("$2a$10$GidMYSDKXr43.ItokilNPeQbFYczwwpC6x5W3qy2L7AYCRoDmPEUi");
		user.setNickname("テストユーザー２");
		user.setStatus("学生");
		user.setProfile("学生です");
		//誕生日は省く
		assertTrue(userMapper.insert(user));
	}

    @Test
    public void update() {
    	User initUser = userMapper.selectInId(4);
    	User user = new User();
    	user.setId(initUser.getId());
    	user.setEmail("testnew@xxx.co.jp");
    	user.setNickname("テストユーザー新");
    	user.setBirthday(initUser.getBirthday());
    	user.setStatus("主婦");
    	user.setProfile("主婦に変更");
    	
    	assertTrue(userMapper.update(user));
    }
	
//削除
	
}

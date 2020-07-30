//package com.example.demo.domain.repository.mybatis;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.example.demo.domain.model.Chara;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@Transactional
//@Sql("/testdata.sql")
//public class TestCharaMapper {
//	
//	@Autowired
//	CharaMapper charaMapper;
//	
//	@Test
//	public void selectTest1() {
//		List<Chara> chara = charaMapper.selectAll();
//		System.out.println(chara.get(49).getId());
//		
//		assertEquals(50, chara.get(49).getId()); 
//		assertEquals("仮の性格", chara.get(49).getKind());
//		
//		assertEquals(51, chara.get(50).getId()); 
//		assertEquals("仮の性格２", chara.get(50).getKind());				
//	}
//	
//	public void selectTest2() {
//		Chara chara = charaMapper.selectInTweet(100);
//		
//		assertEquals(50, chara.getId()); 
//		assertEquals("仮の性格", chara.getKind());						
//	}
//	
//	public void selectTest3() {
//		Chara chara =  charaMapper.selectOne(50);
//
//		assertEquals(50, chara.getId()); 
//		assertEquals("仮の性格", chara.getKind());	
//		
//	}
//
//}

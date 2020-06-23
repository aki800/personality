package com.example.demo.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.demo.domain.model.Chara;

@Mapper
public interface CharaMapper {
	
	@Select("SELECT id AS id,"
			+ " kind AS kind"
	        + " FROM chara")
	public List<Chara> selectAll();
	
	@Select("SELECT *"
			+ " FROM chara"
			+ " WHERE id = #{id}")
	public Chara selectOne(int id);
	

}

package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	//블로그 메인
	public void selectOne(String id) {
		System.out.println("[BlogDao] selectOne()");
		
		sqlSession.selectOne("blog.selectOne", id);
	}
	
	
}

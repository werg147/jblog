package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	//블로그 메인
	public BlogVo selectOne(String id) {
		System.out.println("[BlogDao] selectOne()");
		
		return sqlSession.selectOne("blog.selectOne", id);
	}
	
	//기본 blogTitle저장
	public void insertTitle(BlogVo blogVo) {
		System.out.println("[BlogDao] insertTitle()");
		System.out.println(blogVo);
		
		sqlSession.insert("blog.insertTitle", blogVo);
	}
	
	
	//블로그관리 - 기본설정 - 블로그제목, 로고파일 변경
	public void update(BlogVo blogVo) {
		System.out.println("[BlogDao] update()");
		
		int count = sqlSession.update("blog.update", blogVo);
		System.out.println("성공여부" + count);
	}
	
}

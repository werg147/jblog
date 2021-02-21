package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

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
	
	//블로그 관리 - 카테고리 리스트 가져오기
	public List<CategoryVo> selectList(String id) {
		System.out.println("[BlogDao] cateList()");

		return sqlSession.selectList("category.selectList", id);
	}
	
	
	
}

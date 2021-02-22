package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	//블로그 메인
	public BlogVo selectOne(String id) {
		System.out.println("[BlogDao] selectOne()");
		
		return sqlSession.selectOne("blog.selectOne", id);
	}
	
	//기본 blog정보 저장
	public void insertOne(BlogVo blogVo) {
		System.out.println("[BlogDao] insertOne()");
		System.out.println(blogVo);
		
		sqlSession.insert("blog.insertOne", blogVo);
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
	
	
	//블로그 관리 - 카테고리 추가등록
	public int cateInsert(CategoryVo cateVo) {
		System.out.println("[blogDao] cateInsert()");

		sqlSession.insert("category.cateInsert", cateVo);

		return cateVo.getCateNo();
	}
	
	//카테고리 추가등록시 정보하나 가져오기
	public CategoryVo selectOne(int cateNo) {
		System.out.println("[blogDao] selectOne()");
		
		return sqlSession.selectOne("category.selectOne", cateNo);
	}
	
	//카테고리 삭제
	public int delete(int cateNo) {
		System.out.println("[BlogDao] delete()");
		
		//int count = sqlSession.delete("category.delete", cateNo);
		//System.out.println(count);
		
		return sqlSession.delete("category.delete", cateNo);
	}
	
	//블로그 관리 - 글작성 폼
	public List<CategoryVo> selectForm(String id) {
		System.out.println("[blogDao] selectForm()");
		
		return sqlSession.selectList("category.selectForm", id);
	}
	
	//글 작성
	public void insert(PostVo postVo) {
		System.out.println("[BlogDao] insert()");
		
		sqlSession.insert("post.insert", postVo);
	}
	
	
}

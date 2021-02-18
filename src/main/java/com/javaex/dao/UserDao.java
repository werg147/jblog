package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	public int insert(UserVo userVo) {
		System.out.println("[UserDao] insert()");
		
		return sqlSession.insert("user.insert", userVo);
	}
	
	//로그인
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao] selectUser()");
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
	
}

package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;

	//블로그 메인
	public void blogMain(String id) {
		System.out.println("[BlogService] blogMain()");
		System.out.println(id);
		
		blogDao.selectOne(id);
	}
	
}

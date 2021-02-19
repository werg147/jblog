package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	// 블로그 메인
	public BlogVo blogMain(String id) {
		System.out.println("[BlogService] blogMain()");

		return blogDao.selectOne(id);
	}

	// 블로그관리 - 기본설정
	public BlogVo basic(String id) {
		System.out.println("[BlogService] basic()");

		return blogDao.selectOne(id);
	}
	
	//블로그관리 - 기본설정 - 블로그제목, 로고파일 변경
	public void update(BlogVo blogVo, MultipartFile file) {
		System.out.println("[BlogService] update()");
		
		//db저장 정보수집(저장할 경로)
		
		//오리지널 파일이름
		
		//확장자
		
		//서버저장 파일이름 (세계시간+랜덤숫자+확장자)
		
		//서버 파일패스 -> 저장경로 + \\ + 저장파일이름
		
		//파일사이즈
		
		//서버 하드디스크 저장 - file(jpg)
		
		//db저장 - 파일정보
		
		
	}

}

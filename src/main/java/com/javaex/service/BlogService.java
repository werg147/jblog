package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

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
	public void modify(BlogVo blogVo, MultipartFile file) {
		System.out.println("[BlogService] modify()");
		
		//db저장 정보수집(저장할 경로)
		String saveDir = "C:\\javastudy\\upload";
		System.out.println("saveDir: " + saveDir);
		
		//오리지널 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		//확장자
		 String exName = orgName.substring(orgName.lastIndexOf("."));
		 System.out.println("exName: " + exName);
		
		//서버저장 파일이름 (세계시간+랜덤숫자+확장자)
		String logoFile = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("logoFile(saveName): " + logoFile); 
		
		//서버 파일패스 -> 저장경로 + \\ + 저장파일이름
		String filePath = saveDir + "\\" + logoFile;
		System.out.println("filePath: " + filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: " + fileSize);
		
		//서버 하드디스크 저장 - file(jpg)
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//db저장 - 파일정보
		blogVo.setLogoFile(logoFile);
		//System.out.println("db저장: " + blogVo);
		
		blogDao.update(blogVo);
		
	}

}

package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 메인  -> 관리페이지 작업 후 업데이트
	@RequestMapping(value="/{id}")  //없는 아이디 조회시 에러창띄우기 ->나중에
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController] blogMain()");
		//System.out.println(id);
		
		//id로 회원정보 조회해서 가져오기 > jsp에서도 회원정보로 적용하기
		BlogVo blogVo = blogService.blogMain(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}
	
	//블로그관리-기본설정
	@RequestMapping(value="/{id}/admin/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public String basic(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController] basic()");
		
        BlogVo blogVo = blogService.basic(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-basic";
	}
	
	//블로그관리-기본설정-설정변경
	@RequestMapping(value="/{id}/admin/basic/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@PathVariable("id") String id, 
			             @RequestParam("blogTitle") String blogTitle, 
			             @RequestParam(value="file", required=false) MultipartFile file ) {
		
		System.out.println("[BlogController] modify()");
		
		//System.out.println(blogTitle);
		//System.out.println(file.getOriginalFilename());
		
		BlogVo blogVo = new BlogVo(id, blogTitle);
		blogService.modify(blogVo, file);
		
		
		//설정바꾼후 리다이렉트-기본설정
		return "redirect:/" + id + "/admin/basic";
	}
	
	//블로그관리-카테고리
	@RequestMapping(value="/{id}/admin/cate", method= {RequestMethod.GET, RequestMethod.POST})
	public String cate(@PathVariable("id") String id) {
		System.out.println("[BlogController] cate()");
		
		return "blog/admin/blog-admin-cate";
	}

}

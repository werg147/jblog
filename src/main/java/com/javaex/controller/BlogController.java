package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 메인
	@RequestMapping(value="/{id}")  //없는 아이디 조회시 에러창띄우기 ->나중에
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController] blogMain()");
		//System.out.println(id);
		
		//id로 회원정보 조회해서 가져오기 > jsp에서도 회원정보로 적용하기
		BlogVo blogVo = blogService.blogMain(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}

}

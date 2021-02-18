package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	//회원가입 폼
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("[UserController] joinForm()");
		
		return "user/joinForm";
	}
	
	
	//회원가입
	@RequestMapping(value="/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController] join()");
		//System.out.println(userVo);
		
		int count = userService.join(userVo);
		System.out.println(count);
		
		return "user/joinSuccess";
	}
	
	
	//로그인 폼
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("[UserController] loginForm()");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController] login()");
		
		//아이디 비밀번호가 맞으면 로그인 -> (세션에 이름이나 아이디 담기?)
		//아이디 비밀번호가 틀리면 로그인 실패 -> 다시 입력해주세요. result=fail
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser == null) {
			return "redirect:/user/loginForm?result=fail";
		} else {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}
		
	}
	
	
}

package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 메인  
	@RequestMapping(value="/{id}")
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
	
	//리스트 뿌리기 -> 카테고리 추가  (포스트 갯수? 포스트 갯수를 카운트?)
	
	//블로그관리-카테고리 화면
	@RequestMapping(value="/{id}/admin/category", method= {RequestMethod.GET, RequestMethod.POST})
	public String cate(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController] cate()");
		
		//블로그헤더-블로그타이틀 출력 정보 보내기
        BlogVo blogVo = blogService.blogMain(id);
        model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-cate";
	}
	
	//블로그관리-카테고리 리스트
	@ResponseBody
	@RequestMapping(value="/{id}/admin/cateList", method= {RequestMethod.GET, RequestMethod.POST})
	public List<CategoryVo> cateList(@PathVariable("id") String id) {
		System.out.println("[BlogController] cateList()");
		
		return blogService.cateList(id);
	}
	
	//블로그관리-카테고리 추가등록
	@ResponseBody
	@RequestMapping(value="/{id}/admin/catePlus", method= {RequestMethod.GET, RequestMethod.POST})
	public CategoryVo catePlus(@ModelAttribute CategoryVo categoryVo) {
		System.out.println("[BlogController] catePlus()");
		//System.out.println(cateVo.toString());
		
		CategoryVo cateVo = blogService.catePlus(categoryVo);
		//System.out.println(count);
		
		return cateVo;
	}
	
	//블로그관리- 카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/{id}/admin/remove", method= {RequestMethod.GET, RequestMethod.POST})
	public int remove(@ModelAttribute CategoryVo cateVo) {
		System.out.println("[BlogController] remove()");
		//System.out.println(cateVo);
		int cateNo = cateVo.getCateNo();
		System.out.println(cateNo);
		
		int count = blogService.remove(cateNo);
		System.out.println(count);
		
		return count;
	}
	
	//블로그관리- 글작성 폼
	@RequestMapping(value="/{id}/admin/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(@PathVariable String id, Model model) {
		System.out.println("[BlogController] writeForm()");
		//카테고리명 가져와서 담아서 포워드
		List<CategoryVo> cateList = blogService.writeForm(id);
		
		model.addAttribute("cateList", cateList);
		
		//블로그헤더-블로그타이틀 출력 정보 보내기
        BlogVo blogVo = blogService.blogMain(id);
        model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-write";
	}
	
	//글 작성
	@RequestMapping(value="/{id}/admin/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@PathVariable String id, @ModelAttribute PostVo postVo) {
		System.out.println("[BlogController] write()");
		//System.out.println(postVo);
		
		blogService.write(postVo);
		
		return "redirect:/" + id + "/admin/writeForm";
	}
	
	

}

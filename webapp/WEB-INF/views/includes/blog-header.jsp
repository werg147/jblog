<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
		
			<!-- 블로그 제목 정보가 있으면 blogTitle > 없으면 기본으로 ㅇㅇㅇ의 블로그입니다. -->
			<!-- blogTitle not null -> 회원가입시 자동으로 기본타이틀까지 저장되어야함? 나중에 -->
		
				<h1><a href="${pageContext.request.contextPath}/${blogVo.id}">${blogVo.blogTitle}</a></h1>
			
			<ul class="clearfix">
				<!-- 로그인 전 메뉴 -->
				<li><a class="btn_s" href="">로그인</a></li>
				
				<!-- 로그인 후 메뉴 -->
				<!-- 자신의 블로그일때만 관리 메뉴가 보인다. -->
				<!-- 
				<li><a class="btn_s" href="">내블로그 관리</a></li>
				<li><a class="btn_s" href="">로그아웃</a></li>
		 		-->
			</ul>
		</div>
		<!-- //header -->
		
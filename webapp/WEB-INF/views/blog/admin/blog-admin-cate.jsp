<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>




</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>


		<div id="content">
			<ul id="admin-menu" class="clearfix">
			    <!-- 요청된 리소스 [/jblog//admin/basic]은(는) 가용하지 않습니다. -->
			    <!-- 관리 페이지마다 기본으로 id값을 받아와야하는 번거로움? -> 로그인유저만 볼 수 있는 페이지 -> 세션값으로 id받기 -->
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		
		      		<tbody id="cateList">
		      		<!-- 리스트 영역 ajax방식-->
		      		<%--
		      		 
						<tr id="t-${cateVo.cateNo}">
							<td>${cateVo.cateNo}</td>
							<td>${cateVo.cateName}</td>
							<td>0</td>
							<td>${cateVo.description}</td>
						    <td class='text-center'>
						    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						    </td>
						</tr>		
					--%>
					</tbody>
				</table>
				
				<input type="hidden" id="id" name="id" value="${authUser.id}">
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">


	//javascript경로 잊지말고 써주기 -> Uncaught ReferenceError: $ is not defined 에러 이유 :  jQuery js가 로딩이 안됨
	//DOM 생성되면
	$("document").ready(function(){
		console.log("ready");
		
		//리스트출력
		fetchList();
	});
	
	//id 데이터
	var id = "${authUser.id}";
    console.log(id);
	
	//카테고리 리스트 출력
	function fetchList(){
	
		$.ajax({

			url : "${pageContext.request.contextPath}/" + id + "/admin/cateList",
			type : "post",
			//contentType : "application/json",
			data : {id: id},

			dataType : "json",
			success : function(cateList) {
				/* 성공했을때 */
				console.log(cateList);
				
				for(var i=0; i<cateList.length; i++){
					render(cateList[i], "down");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	
	//카테고리 추가버튼 클릭
	$("#btnAddCate").on("click", function(){
		console.log("추가버튼 클릭")
		
		//등록 데이터(카테고리명,설명)
		var cateName = $("[name='name']").val();
		var	description = $("[name='desc']").val();
		console.log(cateName);
		console.log(description);
		

	
	//저장요청
		$.ajax({

			url : "${pageContext.request.contextPath }/" + id + "/admin/catePlus",
			type : "post",
			//contentType : "application/json",
			data : {cateName: cateName, description: description},

			dataType : "json",
			success : function(cateVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(cateVo);
				render(cateVo, "up");
				
				/* 입력폼 비우기 */
				$("[name='cateName']").val("");
				$("[name='description']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	
	//카테고리 삭제
	$(".btnCateDel").on("click", function(){
		console.log("삭제버튼 클릭");
		
	});
	
	

	//tobody영역에 id값 넣어서 뿌리면 출력안됨
	//카테고리 리스트 정보 + html조합
	function render(cateVo, updown) {

		var str = "";

		str += '	<tr id="t-' + cateVo.cateNo + '">';
		str += '		<td>' + cateVo.cateNo + '</td>';
		str += '		<td>' + cateVo.cateName + '</td>';
		str += '		<td>' + 0 + '</td>';
		str += '		<td>' + cateVo.description + '</td>';
		str += '	    <td class="text-center">';
		str += '	    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '	    </td>';
		str += '	</tr>';

		if (updown == "down") {
			$("#cateList").append(str);
		} else if (updown == "up") {
			$("#cateList").prepend(str);
		} else {
			console.log("방향 미지정");
		}

	}
</script>



</html>
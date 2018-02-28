<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시판 리스트</title>
	<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css" />
	
</head>
<body>

<div class="container">
	<br>
	<p class="text-center h2">게시판 리스트</p>
	<br>
	<table class="table table-hover">
		<thead class="thead-light">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${list}" var="boardVO">
			<tr>
				<td class="text-center">${boardVO.id}</td>
				<td><a href="/board/read?id=${boardVO.id}">${boardVO.title}</a></td>
				<td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${boardVO.date}" /></td>
			</tr>
		</c:forEach>
		</tbody>
		
	</table>
	
	<br>
	<button class="btn btn-primary float-right" type="button" id="btnInsert" onclick="location.href='${path}/board/create'">글쓰기</button>
	
	<div class="center-block">
		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
			<li class="page-item active"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#">4</a></li>
			<li class="page-item"><a class="page-link" href="#">5</a></li>
			<li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
		</ul>
	</div>
	
</div>	

	<!-- bootstrap.js 파일이 jQuery로 구성되어 있기 때문에 먼저 라이브러리 로드 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<!-- 스크립트 파일을 마지막에 적는 이유: 브라우저가 스크립트 파일을 읽는 데 시간이 어느 정도 걸리기 대문에 제일 마지막에 로드하기 위해 -->
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
</body>
</html>
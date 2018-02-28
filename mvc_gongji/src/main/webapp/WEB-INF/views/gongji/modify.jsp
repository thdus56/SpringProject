<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>글 수정 페이지</title>
	<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css" />
</head>
<body>

<div class="container">
	<br>
	<p class="text-center h2">글 수정 페이지</p>
	<br>
	<form id="updateForm" method="POST">
	
	<table class="table table-bordered">
		<tr>
			<th class="table-primary" scope="row">번호</th>
			<td>${boardVO.id}<input type="hidden" name="id" value="${boardVO.id}"></td>
		</tr>
		<tr>
			<th class="table-primary" scope="row">제목</th>
			<td><input type="text" class="form-control" name="title" value="${boardVO.title}"></td>
		</tr>
		<tr>
			<th class="table-primary" scope="row">날짜</th>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${boardVO.date}" /></td>
		</tr>
		<tr>
			<th class="table-primary" scope="row">내용</th>
			<td><textarea class="form-control" name="content" rows="15" cols="50">${boardVO.content}</textarea></td>
		</tr>
	</table>
	
	<div class="btn-group float-right">
		<button type="button" class="btn btn-primary" id="btnList" onclick="location.href='${path}/gongji/listAll'">취소</button>
		<input type="submit" class="btn btn-primary" value="수정">
	</div>
	
	</form>

</div>
	
	<!-- bootstrap.js 파일이 jQuery로 구성되어 있기 때문에 먼저 라이브러리 로드 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<!-- 스크립트 파일을 마지막에 적는 이유: 브라우저가 스크립트 파일을 읽는 데 시간이 어느 정도 걸리기 대문에 제일 마지막에 로드하기 위해 -->
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
</body>
</html>
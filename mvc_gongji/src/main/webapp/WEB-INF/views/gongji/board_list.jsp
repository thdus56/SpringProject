<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<thead>
			<tr class="table-primary">
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성일</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${boardList}" var="boardVO">
			<tr>
				<td class="text-center">${boardVO.id}</td>
				<td><a href="/board/detail?id=${boardVO.id}">${boardVO.title}</a></td>
				<td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${boardVO.date}" /></td>
				<td class="text-center">${boardVO.viewcnt}</td>
			</tr>
		</c:forEach>
		</tbody>
		
	</table>
	
	<br>
	<button class="btn btn-primary float-right" type="button" id="btnInsert" onclick="location.href='${path}/board/create'">글쓰기</button>
	
	<div class="center-block">
		<ul class="pagination justify-content-center">
		
			<!-- 시작 페이지 -->
			<li class="page-item">
				<c:if test="${page.curPage > 1 }">
				<a class="page-link" href="${path}/board/board_list.do?curPage=1">시작</a>
				</c:if>
			</li>
			<!-- 이전 블록 -->
			<li class="page-item">
				<c:if test="${page.curBlock > 1 }">
				<a class="page-link" href="${path}/board/board_list.do?curPage=${page.prevPage}">이전</a>
				</c:if>
			</li>
			
			<!-- 페이지 번호 출력 -->
			<c:forEach var="pageNum" begin="${page.blockStart}" end="${page.blockEnd}">
				<c:choose>
				    <c:when test="${pageNum==page.curPage }">
					    <li class="page-item active">
					    	<a class="page-link" href="${path}/board/board_list.do?curPage=${pageNum}">${pageNum}</a>
					    <li>
				    </c:when>
				    
				    <c:otherwise>
					    <li class="page-item">
							<a class="page-link" href="${path}/board/board_list.do?curPage=${pageNum}">${pageNum}</a>
					    </li>
				    </c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 블록-->
			<li class="page-item">
				<c:if test="${page.curBlock <= page.totBlock}">
				<a class="page-link" href="${path}/board/board_list.do?curPage=${page.nextPage}">다음</a>
				</c:if>
			</li>
			
			<!-- 마지막 페이지 -->
			<li class="page-item">
				<c:if test="${page.curPage < page.totPage}">
					<a class="page-link" href="${path}/board/board_list.do?curPage=${page.totPage}">끝</a>
				</c:if>
			</li>
			
		</ul>
	</div>
	
</div>	

	<!-- bootstrap.js 파일이 jQuery로 구성되어 있기 때문에 먼저 라이브러리 로드 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<!-- 스크립트 파일을 마지막에 적는 이유: 브라우저가 스크립트 파일을 읽는 데 시간이 어느 정도 걸리기 대문에 제일 마지막에 로드하기 위해 -->
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
</body>
</html>
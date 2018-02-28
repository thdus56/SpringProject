<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세 보기</title>
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css" />

<!-- bootstrap.js 파일이 jQuery로 구성되어 있기 때문에 먼저 라이브러리 로드 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- 스크립트 파일을 마지막에 적는 이유: 브라우저가 스크립트 파일을 읽는 데 시간이 어느 정도 걸리기 대문에 제일 마지막에 로드하기 위해 -->
<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>

<script>
function goList() {
	read.method = "get";
	read.action = "/gongji/listPage";
	read.submit();
}

function goUpdate() {
	read.method = "get";
	read.action = "/gongji/modifyPage";
	read.submit();
}

function goDelete() {
	read.action = "/gongji/removePage";
	read.submit();
}
</script>
	
<script id="template" type="text/x-handlebars-template">
<table class="table table-bordered">
{{#each .}}
<tr class="replyLi" data-commentid={{comment_id}}>
	<td class="timeline-item">
		<p class="timeline-header text-secondary font-weight-light">{{comment_id}} - {{comment_writer}}<br>{{prettifyDate comment_date}}</p>
		
		<p class="timeline-body font-weight-bold">{{comment_content}}</p>
		
		<div class="timeline-footer">
			<a class="btn btn-light" role="button" data-toggle="modal" data-target="#modifyModal">댓글 수정</a>
		</div>
	</td>
</tr>
{{/each}}
</table>
</script>

<script>
Handlebars.registerHelper("prettifyDate", function(timeValue){
	var dateObj = new Date(timeValue);
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth() + 1;
	var date = dateObj.getDate();
	var hour = dateObj.getHours();
    var minute = dateObj.getMinutes();
	return year+"-"+month+"-"+date+" "+hour+":"+minute;
});

var printData = function (replyArr, target, templateObject) {
	
	var template = Handlebars.compile(templateObject.html());
	
	var html = template(replyArr);
	$(".replyLi").remove();
	target.after(html);
	
}
</script>	
	
<script>
var id = ${boardVO.id};
var replyPage = 1;

$(document).ready(function() {

	
	function getPage(pageInfo) {
		$.getJSON(pageInfo, function(data) {
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));
			
			$("#modifyModal").modal('hide');
		});
	}
	
	var printPaging = function(pageMaker, target) {
		var str = "";
		
		if (pageMaker.prev) {
			str += "<li class='page-item'><a class='page-link' href='"+(pageMaker.startPage-1)+"'> << </a></li>";
		}
		
		for (var i=pageMaker.startPage, len=pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i? ' active' : '';
			str += "<li class='page-item'"+strClass+"><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		
		if (pageMaker.next) {
			str += "<li class='page-item'><a class='page-link' href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
		}
		
		target.html(str);
	}
	
	$("#repliesDiv").on("click", function() {
		
		if($(".timeline li").size() > 1) {
			return;
		}
		
		getPage("/replies/" + id + "/1");
	});
	
	$(".pagination").on("click", "li a", function(event) {
		event.preventDefault();
		replyPage = $(this).attr("href");
		//alert("id: " + id + "/replyPage: " +replyPage);
		getPage("/replies/" + id + "/" + replyPage);
	
	});

	
	$("#replyAddBtn").on("click", function() {
		
		var replyerObj = $("#newReplyWriter");
		var replytextObj = $("#newReplyText");
		var comment_writer = replyerObj.val();
		var comment_content = replytextObj.val();
		
		$.ajax({
			type:'post',
			url:'/replies/',
			headers: {
				"Content-type": "application/json",
				"X-HTTP-Method-Override": "POST" },
			dataType: 'text',
			data: JSON.stringify({id:id, comment_writer:comment_writer, comment_content:comment_content}),
			success:function(result) {
				console.log("result:" + result);
				if (result == 'SUCCESS') {
					alert("댓글이 등록되었습니다.");
					replyPage = 1;
					getPage("/replies/" + id + "/" + replyPage);
					replyerObj.val("");
					replytextObj.val("");
				}
			}});
	});
	
	$(".timeline").on("click", ".replyLi", function(event){
		
		var reply = $(this);
		
		$("#comment_content").val(reply.find('.timeline-body').text());
		$(".modal-title").html(reply.attr("data-commentid"));
	
	});
	
	$("#replyModBtn").on("click", function() {
		
		var comment_id = $(".modal-title").html();
		var comment_content = $("#comment_content").val();
		//alert(comment_id);
		//alert(comment_content);
		
		$.ajax({
			type:'put',
			url:'/replies/'+comment_id,
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "PUT" },
			data:JSON.stringify({comment_content:comment_content}),
			dataType:'text',
			success:function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("댓글이 수정되었습니다.");
					getPage("/replies/"+id+"/"+replyPage);
				}
		}});
	});
	
	$("#replyDelBtn").on("click", function() {
		
		var comment_id = $(".modal-title").html();
		var comment_content = $("#comment_content").val();
		
		$.ajax({
			type:'delete',
			url:'/replies/'+comment_id,
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "DELETE" },
			dataType:'text',
			success:function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("댓글이 삭제되었습니다.");
					getPage("/replies/"+id+"/"+replyPage);
				}
		}});
	});
	
	
});
	
</script>
</head>
<body>

<div class="container">
	<br>
	<p class="text-center h2">게시글 보기</p>
	<br>
	<form name="read" action="modifyPage" method="post">
		
		<input type="hidden" name="id" value="${boardVO.id}">
		<input type="hidden" name="page" value="${cri.page}">
		<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
		
		<table class="table table-bordered">
			<tr>
				<th class="table-primary" scope="row">번호</th>
				<td>${boardVO.id}</td>
			</tr>
			<tr>
				<th class="table-primary" scope="row">제목</th>
				<td>${boardVO.title}</td>
			</tr>
			<tr>
				<th class="table-primary" scope="row">작성일</th>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${boardVO.date}" /></td>
			<tr>
				<th class="table-primary" scope="row">조회수</th>
				<td>${boardVO.viewcnt}</td>
			</tr>
			<tr>
				<th class="table-primary" scope="row">내용</th>
				<td>${boardVO.content}</td>
			</tr>
		</table>
			
		<div class="btn-group float-right">
			<button type="button" class="btn btn-primary"  id="btnList" onclick="goList()">목록</button>
			<input type="button" class="btn btn-primary " id="btnUpdate" value="수정" onclick="goUpdate()">
			<input type="button" class="btn btn-primary " id="btnDelete" value="삭제" onclick="goDelete()">
		</div>
	
	</form>
	
	<br><br>
	<div class="row">
		<div class="col-md-12">
		
			<div class="box box-success">
				
				<div class="box-header">
					<h2 class="box-title">댓글 쓰기</h2>
				</div>
				
				<div class="box-body">
					<label for="newReplyWriter">작성자</label>
						<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter">
					<label for="newReplyText">댓글 내용</label>
						<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText">	
				</div>
				<br>
				<div class="box-footer">
					<button type="submit" class="btn btn-primary" id="replyAddBtn">확인</button>
				</div>
			
			</div>
	
		</div>
	</div>
	
	<br><br>
	<ul class="timeline">
		<li class="time-label"><button type="button" class="btn btn-success" id="repliesDiv">댓글 목록</button><br></li>
	</ul>
	
	<div class="text-center">
		<ul id="pagination" class="pagination pagination-sm justify-content-center no-margin"></ul>
	</div>
	
	<div id="modifyModal" class="modal modal-primary fade" role="dialog">
		<div class="modal-dialog">
		
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-left"></h4>
					<h3></h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<div class="modal-body" data-commentid>
					<p><input type="text" id="comment_content" class="form-control"></p>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="replyModBtn">수정</button>
					<button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
			
		</div>
	</div>
	
</div>


</body>
</html>
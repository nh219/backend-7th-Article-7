<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>게시판</title>
	 	
	</head>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='readForm']");
			
			// 수정 
			$(".update_btn").on("click", function(){
				formObj.attr("action", "/backend-7th-Article-7/post/postUpdateTest");
				formObj.attr("method", "get");
				formObj.submit();				
			})
			
			// 삭제
			$(".delete_btn").on("click", function(){
				formObj.attr("action", "/backend-7th-Article-7/post/postDeleteProcess");
				formObj.attr("method", "post");
				formObj.submit();
			})
			
			// 취소
			$(".list_btn").on("click", function(){
				
				location.href = "/backend-7th-Article-7/post/postListTest";
			})
			
			// 댓글 작성
			$(".replyWriteBtn").on("click", function(){
				  var formObj = $("form[name='replyForm']");
				  formObj.attr("action", "/backend-7th-Article-7/post/replyWriteProcess");
				  formObj.submit();
				});
		})
	</script>
	
	<body>
	
		<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<nav>
				<a href="<c:url value='/main' />">홈</a></li>
			</nav>
			<hr />

		<section id="container">
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="postId" name="postId"
					value="${read.postId}" />
			</form>
			<table>
				<tbody>
					<tr>
						<td><label for="title">제목</label><input type="text"
							id="title" name="title" value="${read.title}" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td><label for="content">내용</label> <textarea id="content"
								name="content" readonly="readonly"><c:out
									value="${read.content}" /></textarea></td>
					</tr>
					<tr>
						<td><label for="nickname">작성자</label><input type="text"
							id="nickname" name="nickname" value="${read.nickname}"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td><label for="postRegistTime">작성날짜</label> <fmt:formatDate
								value="${read.postRegistTime}" type="both" /></td>
					</tr>
				</tbody>
			</table>
			<div>
				<button type="submit" class="update_btn">수정</button>
				<button type="submit" class="delete_btn">삭제</button>
				<button type="submit" class="list_btn">목록</button>
			</div>

			<!-- 댓글 -->
			<div id="reply">
				<c:forEach items="${replyList}" var="replyList">
					<p>
						작성자 : ${replyList.nickname}<br /> 작성 날짜 :
						<fmt:formatDate value="${replyList.replyRegistTime}" type="both" />
					</p>
					<p>${replyList.content}</p>
				</c:forEach>
			</div>
			<form name="replyForm" method="post">
				<input type="hidden" id="postId" name="postId"
					value="${read.postId}" /> <input type="hidden" id="page"
					name="page" value="${scri.page}"> <input type="hidden"
					id="perPageNum" name="perPageNum" value="${scri.perPageNum}">
				<input type="hidden" id="searchType" name="searchType"
					value="${scri.searchType}"> <input type="hidden"
					id="keyword" name="keyword" value="${scri.keyword}">

				<div>
					<label for="nickname">댓글 작성자</label> <input type="text"
						id="nickname" name="nickname" /> <br /> <label for="content">댓글
						내용</label> <input type="text" id="content" name="content" />
				</div>
				<div>
					<button type="button" class="replyWriteBtn">작성</button>
				</div>
				
			</form>
		</section>
		<hr />
		</div>
	</body>
</html>
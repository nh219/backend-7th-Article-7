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
					<input type="hidden" id="postId" name="postId" value="${read.postId}" />
				</form>
					<table>
						<tbody>	
							<tr>
								<td>
									<label for="title">제목</label><input type="text" id="title" name="title" value="${read.title}" readonly="readonly" />
								</td>
							</tr>	
							<tr>
								<td>
									<label for="content">내용</label><textarea id="content" name="content" readonly="readonly"><c:out value="${read.content}"/></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="nickname">작성자</label><input type="text" id="nickname" name="nickname" value="${read.nickname}" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td>
									<label for="postRegistTime">작성날짜</label>
									<fmt:formatDate value="${read.postRegistTime}" type="both"/>
								</td>
							</tr>		
						</tbody>			
					</table>
				<div>
					<button type="submit" class="update_btn">수정</button>
					<button type="submit" class="delete_btn">삭제</button>
					<button type="submit" class="list_btn">목록</button>	
				</div>
			</section>
			<hr />
		</div>
	</body>
</html>
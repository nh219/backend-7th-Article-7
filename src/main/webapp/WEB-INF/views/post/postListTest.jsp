<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
	 	<title>게시판</title>
	</head>
	<body>
		<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<nav>
			  <a href="<c:url value='/main' />">홈</a>
			   -
			  <a href="<c:url value='/post/postWriteTest' />">글 작성</a>
			</nav>
			<hr />
			
			<section id="container">
				<form role="form" method="post" action="/post/write">
					<table>
						<tr><th>제목</th><th>작성자</th><th>등록일</th></tr>
						
						<c:forEach items="${list}" var = "list">
							<tr>
								<td>
								<a href="<c:url value='/post/postReadTest?postId=${list.postId}'/>">${list.title}</a>
								</td>
								<td>${list.nickname}</td>
								<td><fmt:formatDate value="${list.postRegistTime}" type="both"/></td>
							</tr>
						</c:forEach>
						
					</table>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
	 	<title>게시판</title>
	 	
	 	<!-- 페이징 가로 정렬 -->
	 	<style type="text/css">
			li {list-style: none; float: left; padding: 6px;}
		</style>
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
				<div>
					<ul>
						<c:if test="${pageMaker.prev}">
							<li><a
								href="postListTest${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
						</c:if>

						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
							<li><a href="postListTest${pageMaker.makeQuery(idx)}">${idx}</a></li>
						</c:forEach>

						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<li><a href="postListTest${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
						</c:if>
					</ul>
				</div>
			</form>
			</section>
			<hr />
		</div>
	</body>
</html>
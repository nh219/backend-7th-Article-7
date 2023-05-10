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
				<a href="<c:url value='/main' />">홈</a></li>
				-
				<a href="<c:url value='/post/postUpdateTest' />">글 수정</a>
			</nav>
			<hr />
			
			<section id="container">
				<form role="form" method="post">
					<table>
						<tbody>
							<tr>
								<td>
									<label for="postId">글 번호</label><input type="text" id="postId" name="postId" value="${read.postId}" disabled/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="title">제목</label><input type="text" id="title" name="title" value="${read.title}" disabled />
								</td>
							</tr>	
							<tr>
								<td>
									<label for="content">내용</label><textarea id="content" name="content" readonly><c:out value="${read.content}"/></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="nickname">작성자</label><input type="text" id="nickname" name="nickname" value="${read.nickname}" disabled/>
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
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>


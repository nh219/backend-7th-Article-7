<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

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
			</nav>
			<hr />
			
			<section id="container">
				<form:form action="postWriteProcess" modelAttribute="formData" id="registerForm" method="POST">
					<table>
						<tbody>
							<tr>
								<td>
									<label for="title">제목</label><input type="text" id="title" name="title" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="nickname">작성자</label><input type="text" id="nickname" name="nickname" value="${auth.nickname}"/>
								</td>
							<tr>
							<tr>
								<td>
									<label for="content">내용</label><textarea id="content" name="content" ></textarea>
								</td>
							</tr>
								<td>						
									<button type="submit">작성</button>
								</td>
							</tr>			
						</tbody>			
					</table>
				</form:form>
			</section>
			<hr />
		</div>
	</body>
</html>
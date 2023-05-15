<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="javax.servlet.http.HttpSession"%>

<%
    HttpSession currentSession = request.getSession(false);
    String alertMessage = (String) currentSession.getAttribute("alert");
    currentSession.removeAttribute("alert");
%>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Teamluck.gg</title>
	<link rel="stylesheet" href="../WebContent/login style.css">
	<% if (alertMessage != null) { %>
        <script>
            alert('<%= alertMessage %>');
        </script>
    <% } %>
</head>
<body>
	<!-- 헤더 영역 -->
	<header>
		<c:url var="mainUrl" value="/main" />
		<div class="logo">
		    <a href="${mainUrl}"><img src="../WebContent/lol-logo.png" alt="Teamluck.gg"></a>
		</div>
		
		<div class="navi">
			<nav>
				<ul>
				
					<li><a href="<c:url value='/post/lolSearch' />">전적검색</a></li>
					<li><a href="<c:url value='/post/community' />">커뮤니티</a></li>
					<li><a href="<c:url value='/post/postCrawl' />">통합 인기글</a></li>
					
					<c:if test="${auth == null }">
					<li><a href="<c:url value='/member/login' />">로그인</a></li>
					<li><a href="<c:url value='/register/step1' />">회원가입</a></li>
					</c:if>
					
					<c:if test="${auth != null }">
					${auth.nickname}<li><a href="<c:url value='/member/logout' />">로그아웃</a></li>
					</c:if>
					
				</ul>
			</nav>
		</div>
		
	</header>

	<!-- 메인 영역 -->
	<main>
		<div class="main section">
			
			<!-- 대문짝 -->
			<div class="main banner">
				<img src="../WebContent/teamluckgglogo2.png" alt="Teamluck.gg">
			</div>

			<!-- 하단 영역 -->
			<div class="ADbanner">

				<!-- 좌측 배너 -->
				<div class="ADbanner left">
				    <a href="https://lafudgestore.com/" target="_blank">
				        <img src="../WebContent/banner1.PNG" alt="Banner Left">
				    </a>
				</div>

				<!-- 콘텐츠 영역 -->
				<div class="member-card-layout__container">
					<div class="member-card-layout__inner">
						<h1 class="member-card-layout__logo">
							<img class="member-card-layout__logo-image" src="" alt="Teamluck.gg"></h1>
						<div class="login">
							<form action="<c:url value='/member/loginProcess' />" method="post">
								<h2 class="login__email-title">이메일 로그인</h2>
								<div class="member-input">
									<div class="member-input__state">
										<input id="memberInput8450" class="member-input__box" type="text" autocomplete="off" name="email" value="" placeholder="이메일">
										<label for="memberInput8450" class="member-input__label"></label>
										<span class="member-input__valid-wrapper"></span>
									</div>
								</div>
								<div class="member-input">
									<div class="member-input__state">
										<input id="memberInput304" class="member-input__box" type="password" autocomplete="off" name="password" value="" placeholder="비밀번호">
										<label for="memberInput304" class="member-input__label"></label>
										<span class="member-input__valid-wrapper"></span>
									</div>
								</div>
								<a href="/main"><button type="submit" class="member-button login__btn">로그인</button></a>
								</button>
								<div class="login__l-sign-up">Teamluck.gg에 처음이세요?
									<span class="login__sign-up-link">
										<a class="member-link" href="../register/step1">회원가입하기</a>
									</span>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- 우측 배너 -->
				<div class="ADbanner right">
				    <a href="https://fifaonline4.nexon.com/main/index" target="_blank">
				        <img src="../WebContent/banner2.PNG" alt="Banner Right">
				    </a>
				</div>
			</div>
		</div>
	</main>
		<script>
		let data = '${loginFailMsg}';
		if(data!=null && data !=""){
			alert(data);
		}
	</script>
</body>
</html>
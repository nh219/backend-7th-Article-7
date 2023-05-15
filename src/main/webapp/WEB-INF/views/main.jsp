<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>메인화면</title>
	<link rel="stylesheet" href="WebContent/home style.css">
</head>
<body>
	<!-- 헤더 영역 -->
	<header>
		<c:url var="mainUrl" value="/main" />
		<div class="logo">
		    <a href="${mainUrl}"><img src="WebContent/lol-logo.png" alt="Teamluck.gg"></a>
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
				<img src="WebContent/teamluckgglogo2.png" alt="Teamluck.gg">
			</div>

			<!--검색창-->
			<div class="search">
				<input type="text" placeholder="Search...">
				<button type="submit">Search</button>
			</div>

			<!-- 하단 영역 -->
			<div class="ADbanner">

				<!-- 좌측 배너 -->
				<div class="ADbanner left">
				    <a href="https://lafudgestore.com/" target="_blank">
				        <img src="WebContent/banner1.PNG" alt="Banner Left">
				    </a>
				</div>


				<!-- 콘텐츠 영역 -->
				<div class="container">

					<div class="layout">
						<!-- 커뮤니티 -->
						<div class="community left">
							<!-- 인기글 게시판 주소 연결 -->
							<h2>
								<a>Teamluck.gg 인기글</a>
							</h2>
							<div class="community-list half">
								<article class="css-lank">
									<!-- 게시글 주소 따와 연결 -->
									<a>
										<div class="community-list ranking">1</div>
									</a>
								</article>

								<article class="css-lank">
									<!-- 게시글 주소 따와 연결 -->
									<a>
										<div class="community-list ranking">2</div>
									</a>
								</article>

								<article class="css-lank">
									<!-- 게시글 주소 따와 연결 -->
									<a>
										<div class="community-list ranking">3</div>
									</a>
								</article>

								<article class="css-lank">
									<!-- 게시글 주소 따와 연결 -->
									<a>
										<div class="community-list ranking">4</div>
									</a>
								</article>

								<article class="css-lank">
									<!-- 게시글 주소 따와 연결 -->
									<a>
										<div class="community-list ranking">5</div>
									</a>
								</article>
							</div>
						</div>


<c:if test="${member != null }">
<div>
<p>${member.nickname}님 환영 합니다.</p>

</div>
</c:if>

							<!-- 우측 커뮤니티 -->
						<div class="community right">
							<div class="community-right section">
								<h2>
									<a>투표 피드백</a>
								</h2>

								<div class="community-list half">
									<article class="css-lank">
										<!-- 게시글 주소 따와 연결 -->
										<a>
											<div class="community-list ranking">1</div>
										</a>
									</article>
	
									<article class="css-lank">
										<!-- 게시글 주소 따와 연결 -->
										<a>
											<div class="community-list ranking">2</div>
										</a>
									</article>
								</div>
							</div>


							<div class="community-right section">
								<h2>
									<a>파티 구함</a>
								</h2>

								<div class="community-list half">
									<article class="css-lank">
										<!-- 게시글 주소 따와 연결 -->
										<a>
											<div class="community-list ranking">1</div>
										</a>
									</article>
	
									<article class="css-lank">
										<!-- 게시글 주소 따와 연결 -->
										<a>
											<div class="community-list ranking">2</div>
										</a>
									</article>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 우측 배너 -->
				<div class="ADbanner right">
				    <a href="https://fifaonline4.nexon.com/main/index" target="_blank">
				        <img src="WebContent/banner2.PNG" alt="Banner Right">
				    </a>
				</div>
			</div>
		</div>
	</main>
</body>
</html>


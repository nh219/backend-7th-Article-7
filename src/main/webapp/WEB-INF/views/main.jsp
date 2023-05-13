<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Teamluck.gg</title>
	<link rel="stylesheet" href="WebContent/home style.css">
</head>
<body>
	<!-- 헤더 영역 -->
	<header>
		<div class="logo">
			<a href="main"><img src="WebContent/lol-logo.png" alt="Teamluck.gg"></a>
		</div>
<%-- 		<li><a href="<c:url value='/member/memberUpdate' />">회원정보 변경</a></li> --%>
<%--		<li><a href="<c:url value='/member/logout' />">로그아웃</a></li> --%>
		<div class="navi">
			<nav>
				<ul>
					<li><a href="<c:url value='/post/lolSearch' />">전적검색</a></li>
					<li><a href="<c:url value='/post/community' />">커뮤니티</a></li>
					<li><a href="<c:url value='/post/postCrawl' />">통합 인기글</a></li>
					<li><a href="<c:url value='/member/login' />">로그인</a></li>
					<li><a href="<c:url value='/register/step1' />">회원가입</a></li>
					<li><a href="<c:url value='/post/postListTest' />">게시판테스트</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<!-- 메인 영역 -->
	<main>
		<div class="main section">
			
			<!-- 대문짝 -->
			<div class="main banner">
				<a href="main"><img src="https://i.ibb.co/fDR0YnX/teamluckgglogo2.png" alt="Teamluck.gg"></a>
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
					<img src="banner_left.png" alt="Banner Left">
				</div>

				<!-- 콘텐츠 영역 -->
				<div class="content">

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
					<img src="banner_right.png" alt="Banner Right">
				</div>
			</div>
		</div>
	</main>
</body>
</html>

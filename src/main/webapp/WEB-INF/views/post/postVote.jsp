<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>투표 피드백 게시판</title>
<link rel="stylesheet" href="../WebContent/community style copy.css">
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
				<div class="content1">
					<div class="sidebar" style>
						<div class="sidebar__inner"
							style="position: relative; transform: translate3d(0px, 0px, 0px);">
							<div class="sidebar-content-wrap">

								<div class="sidebar-content">
									<div class="sidebar-menu">
										<div class="sidebar-menu__title">홈</div>
										<ul class="sidebar-menu__list">
											<li class="sidebar-menu__item sidebar-menu__item--active">
												<a href="<c:url value='/post/community' />"> 전체 </a>
											</li>
										</ul>
									</div>
									<div class="sidebar-menu">
										<div class="sidebar-menu__title">커뮤니티</div>
										<ul class="sidebar-menu__list">
											<li class="sidebar-menu__item "><a href="<c:url value='/post/postFree' />"> 자유 </a></li>
											<li class="sidebar-menu__item "><a href="<c:url value='/post/postVote' />"> 투표 피드백 </a></li>
											<li class="sidebar-menu__item "><a href="<c:url value='/post/postParty' />"> 파티 구함 </a></li>
											<li class="sidebar-menu__item "><a href="<c:url value='/post/postCrawl' />"> 통합 인기글 </a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="board-wrapper">
					<div class="board-header">
						<div class="sort-buttons">
							<button class="btn-popular">인기순</button>
							<button class="btn-latest">최신순</button>
						</div>
						<div class="board-search">
							<form action="" method="GET">
								<select name="search-option">
									<option value="title">제목</option>
									<option value="content">내용</option>
									<option value="titleContent">제목+내용</option>
									<option value="nincname">작성자</option>
								</select> <input type="text" name="search" placeholder="검색어를 입력하세요">
								<button type="submit">검색</button>
							</form>
						</div>
						<div class="board-write">
							<button type="button" onclick="window.location.href='<c:url value='/post/postRegist' />'">글 작성</button>
						</div>
					</div>

					<div class="board-list">
						<section id="container">
							<form role="form" method="post" action="/post/write">
								<table>
									<tr>
										<th>글 번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>조회수</th>
										<th>등록일</th>
									</tr>

									<c:forEach items="${list}" var="list">
										<tr>
											<td>${list.postId}</td>
											<td><a href="<c:url value='/post/postContent?postId=${list.postId}'/>">${list.title}</a></td>
											<td>${list.nickname}</td>
											<td>${list.views}</td>
											<td><fmt:formatDate value="${list.postRegistTime}" type="both" /></td>
										</tr>
									</c:forEach>
								</table>
							</form>
						</section>
						<!-- 추가로 글 목록을 생성하면 됩니다. -->
						<div class="board-footer">
							<div class="board-paging">
								<a href="#" class="prev">이전</a> <a href="#" class="next">다음</a>
							</div>
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
	</main>
</body>

</html>


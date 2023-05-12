<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>통합 인기 게시판</title>
<link rel="stylesheet" href="../WebContent/community style copy.css">
</head>

<body>
	<!-- 헤더 영역 -->
	<header>
		<div class="logo">
			<a href="http://Teamluck.gg.com">
			<img src="../WebContent/lol-logo.png" alt="Teamluck.gg"></a>
		</div>

		<div class="navi">
			<nav>
				<ul>
					<li><a href="<c:url value='/post/lolSearch' />">전적검색</a></li>
					<li><a href="<c:url value='/post/community' />">커뮤니티</a></li>
					<li><a href="<c:url value='/post/postCrawl' />">통합 인기글</a></li>
					<li><a href="<c:url value='/main' />">로그아웃</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<main>
		<div class="main section">
			<!-- 대문짝 -->
			<div class="main banner">
				<img src="https://i.ibb.co/fDR0YnX/teamluckgglogo2.png" alt="Teamluck.gg">
			</div>

			<!-- 하단 영역 -->
			<div class="ADbanner">

				<!-- 좌측 배너 -->
				<div class="ADbanner left">
					<img src="banner_left.png" alt="Banner Left">
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
								</select> <input type="text" name="search" placeholder="검색어를 입력하세요">
								<button type="submit">검색</button>
							</form>
						</div>
						<div class="board-write">
							<a href="<c:url value='/post/postRegist' />">글 작성</a>
						</div>
					</div>

					<div class="board-list">
						<section id="container">
							<form role="form" method="post" action="/post/write">
								<table>
									<tr>
										<th>제목</th>
										<th>작성자</th>
										<th>등록일</th>
									</tr>

									<c:forEach items="${list}" var="list">
										<tr>
											<td><a href="<c:url value='/post/postContent?postId=${list.postId}'/>">${list.title}</a></td>
											<td>${list.nickname}</td>
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

				<div class="ADbanner right">
					<img src="banner_right.png" alt="Banner Right">
				</div>
			</div>
	</main>
</body>

</html>


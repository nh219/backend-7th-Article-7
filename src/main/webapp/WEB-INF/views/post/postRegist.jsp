<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>게시글 작성</title>
 		<link rel="stylesheet" href="../WebContent/write style copy.css">
</head>
<body>
	<!-- 헤더 영역 -->
	<header>
		<div class="logo">
			<a href="http://Teamluck.gg.com"><img src="lol-logo.png" alt="Teamluck.gg"></a>
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
                        <div class="sidebar__inner" style="position: relative; transform: translate3d(0px, 0px, 0px);">
                            <div class="sidebar-content-wrap">
                                
                                <div class="sidebar-content">
                                    <div class="sidebar-menu">
                                        <div class="sidebar-menu__title">홈</div>
                                        <ul class="sidebar-menu__list">
                                            <li class="sidebar-menu__item sidebar-menu__item--active">
                                                <a href="">
                                                    전체
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="sidebar-menu">
                                        <div class="sidebar-menu__title">커뮤니티</div>
                                        <ul class="sidebar-menu__list">
                                            <li class="sidebar-menu__item ">
                                                <a href="">
                                                    자유
                                                </a>
                                            </li>
                                            <li class="sidebar-menu__item ">
                                                <a href="">
                                                    투표 피드백
                                                </a>
                                            </li>
                                            <li class="sidebar-menu__item ">
                                                <a href="">
                                                    파티 구함
                                               </a>
                                            </li>
                                            <li class="sidebar-menu__item ">
                                                <a href="">
                                                     통합 인기글
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="board-wrapper">
                        <div class="board-write">
                            <form:form action="postRegistProcess" modelAttribute="formData" id="registerForm" method="post">
                              <div>
                                <label for="category">카테고리:</label>
                                <select id="category" name="category">
                                  <option value="free">자유</option>
                                  <option value="vote">투표 피드백</option>
                                  <option value="recruitment">파티 구함</option>
                                  <option value="combine">통합 인기 게시판</option>
                                </select>
                              </div>
                              <div>
                                <label for="title">제목:</label>
                                <input type="text" id="title" name="title">
                              </div>
                              <div>
                                <label for="image">이미지:</label>
                                <input type="file" id="image" name="image">
                              </div>
                              <div>
                              	<label for="nickname">작성자</label>
                              	<input type="text" id="nickname" name="nickname" value="${auth.nickname}"/>
                              </div>
                              <div>
                                <label for="content">내용:</label>
                                <textarea id="content" name="content"></textarea>
                              </div>
                              <button type="submit">작성</button>
                            </form:form>
                        </div>
                  </div>
                
                <div class="ADbanner right">
                    <img src="banner_right.png" alt="Banner Right">
                </div>
		    </div>
	</main>
</body>

</html>



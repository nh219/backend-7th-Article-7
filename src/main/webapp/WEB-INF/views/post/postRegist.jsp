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
                    <div class="sidebar">
                        <div class="sidebar__inner" style="position: relative; transform: translate3d(0px, 0px, 0px);">
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
                                            <li class="sidebar-menu__item ">
                                                <li class="sidebar-menu__item "><a href="<c:url value='/post/postFree' />"> 자유 </a></li>
												<li class="sidebar-menu__item "><a href="<c:url value='/post/postVote' />"> 투표 피드백 </a></li>
												<li class="sidebar-menu__item "><a href="<c:url value='/post/postParty' />"> 파티 구함 </a></li>
												<li class="sidebar-menu__item "><a href="<c:url value='/post/postCrawl' />"> 통합 인기글 </a></li>
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
                                  <option value="자유">자유</option>
                                  <option value="투표 피드백">투표 피드백</option>
                                  <option value="파티 구함">파티 구함</option>
                                  <option value="통합 인기 게시판">통합 인기 게시판</option>
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
								  <label for="member_id">작성자 ${auth.nickname}</label>
								  <input type="hidden">
							  </div>
                              
                              
                              <!--  
                              <div>
                              	<label for="nickname">작성자</label>
                              	<input type="text" id="nickname" name="nickname" value="${auth.nickname}"/>
                              </div>
                              -->
                              
                              <div>
                                <label for="content">내용:</label>
                                <textarea id="content" name="content"></textarea>
                              </div>
                              <button type="submit">작성</button>
                            </form:form>
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



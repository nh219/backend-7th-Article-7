<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../WebContent/content style.css">
	<title>게시글 수정</title>
</head>

	<script type="text/javascript">
		$(document).ready(function(){
			
			$(".cancel_btn").on("click", function(){
				event.preventDefault();
				location.href = "/backend-7th-Article-7/post/postListTest";
			})
		})
	</script>

<body>
	<!-- 헤더 영역 -->
	<header>
		<div class="logo">
			<a href="http://Teamluck.gg.com"><img
				src="../WebContent/lol-logo.png" alt="Teamluck.gg"></a>
		</div>

		<div class="navi">
			<nav>
				<ul>
					<li><a href="<c:url value='/post/lolSearch' />">전적검색</a></li>
					<li><a href="<c:url value='/post/community' />">커뮤니티</a></li>
					<li><a href="<c:url value='/post/crawlPost' />">통합 인기글</a></li>
					<li><a href="<c:url value='/main' />">로그아웃</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<main>
		<div class="main section">
			<!-- 대문짝 -->
			<div class="main banner">
				<img src="https://i.ibb.co/fDR0YnX/teamluckgglogo2.png"
					alt="Teamluck.gg">
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
												<a href=""> 전체 </a>
											</li>
										</ul>
									</div>
									<div class="sidebar-menu">
										<div class="sidebar-menu__title">커뮤니티</div>
										<ul class="sidebar-menu__list">
											<li class="sidebar-menu__item "><a href=""> 자유 </a></li>
											<li class="sidebar-menu__item "><a href=""> 투표 피드백 </a>
											</li>
											<li class="sidebar-menu__item "><a href=""> 파티 구함 </a></li>
											<li class="sidebar-menu__item "><a href=""> 통합 인기글 </a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<section id="container">
					<form:form action="postUpdateProcess" modelAttribute="formData" id="updateForm" method="POST">
						<input type="hidden" name="postId" value="${update.postId}" readonly="readonly"/>
						<div class="post-header">
							<span class="category">[카테고리]
							</span>
								<label for="title">제목</label><input type="text" id="title" name="title" value="${update.title}"/>
							<div class="post-info">
								<span clss="postid"> <label for="postId">글 번호</label><input
									type="text" id="postId" name="postId" value="${read.postId}"
									disabled />
								</span> 
								<span class="author">
									<label for="nickname">작성자</label><input type="text" id="nickname" name="nickname" value="${update.nickname}" readonly="readonly"/>
								</span> 
								<span class="date"> 
									<label for="regdate">작성날짜</label>
									<fmt:formatDate value="${update.postRegistTime}" pattern="yyyy-MM-dd"/>	
								</span> 
								<span class="views">조회수</span>
							</div>
						</div>
						<div class="post-content">
							<p>
								<label for="content"></label>
								<textarea id="content" name="content"><c:out value="${update.content}" /></textarea>
							</p>
							<button type="submit" class="update_btn">저장</button>
							<button type="submit" class="cancel_btn">취소</button>
						</div>
					</form:form>
					</div>
				</section>

				<div class="ADbanner right">
					<img src="banner_right.png" alt="Banner Right">
				</div>
			</div>
		</div>
	</main>
</body>

</html>